package leegroup.module.designsystem

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import leegroup.module.designsystem.ui.viewmodel.SavedStateViewModel
import leegroup.module.test.BaseUnitTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SavedStateBaseViewModelTest : BaseUnitTest() {

    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var viewModel: TestSavedStateViewModel
    private val initialState = SampleUiState("initial")

    private val key = "uiState_${SampleUiState::class.simpleName}"

    @Before
    fun setup() {
        savedStateHandle = mockk() // Mock SavedStateHandle
        // Mock the initial state for the SavedStateHandle
        every { savedStateHandle.get<SampleUiState>(key) } returns null
        every { savedStateHandle[key] = any<SampleUiState>() } returns Unit
        viewModel = TestSavedStateViewModel(savedStateHandle, initialState)
    }

    @Test
    fun `test initial state from SavedStateHandle`() = runTest {
        // The initial state should be the provided default value since the saved state is null
        assertEquals(initialState, viewModel.uiState.first())
    }

    @Test
    fun `test state update without saving`() = runTest {
        val newState = SampleUiState("updated")

        // Update the state using the `update` method
        viewModel.updateState { currentState -> currentState.copy(value = newState.value) }

        // Assert that the state has been updated
        assertEquals(newState, viewModel.uiState.first())

        // Verify that the SavedStateHandle has NOT been updated (update doesn't persist state)
        verify(exactly = 0) { savedStateHandle[key] = any<SampleUiState>() }
    }

    @Test
    fun `test state update with saving`() = runTest {
        val newState = SampleUiState("updated")

        // Update the state using the `updateAndSave` method, which saves the new state
        viewModel.updateStateAndSave { currentState -> currentState.copy(value = newState.value) }

        // Assert that the state has been updated
        assertEquals(newState, viewModel.uiState.first())

        // Verify that the state is saved to the SavedStateHandle
        verify { savedStateHandle[key] = newState }
    }

    @Test
    fun `test state persistence when state changes`() = runTest {
        val newState = SampleUiState("updated")

        // Initially set the state
        viewModel.updateStateAndSave { currentState -> currentState.copy(value = "updated") }

        // Ensure that the state has been updated
        assertEquals(newState, viewModel.uiState.first())

        // Mock the SavedStateHandle to return the new state when the ViewModel is reinitialized
        every { savedStateHandle.get<SampleUiState>(key) } returns newState

        // Reinitialize the ViewModel to simulate the process after a configuration change or process death
        val newViewModel = TestSavedStateViewModel(savedStateHandle, initialState)

        // Verify that the persisted state is restored correctly
        assertEquals(newState, newViewModel.uiState.first())
    }

    @Test
    fun `test state not updated when value remains the same`() = runTest {
        val initial = SampleUiState("unchanged")
        val sameState = SampleUiState("unchanged")

        viewModel.updateStateAndSave { currentState -> currentState.copy(value = sameState.value) }
        delay(10)
        viewModel.updateStateAndSave { currentState -> currentState.copy(value = sameState.value) }

        // Verify the SavedStateHandle is not updated when the value doesn't change
        verify(exactly = 1) { savedStateHandle[key] = any<SampleUiState>() }

        // Ensure that the state is still the same
        assertEquals(initial, viewModel.uiState.first())
    }

}

// Sample Parcelable model
private data class SampleUiState(val value: String) : Parcelable {
    override fun describeContents(): Int {
        return 1
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {

    }
}

// A concrete implementation of the abstract ViewModel for testing
private class TestSavedStateViewModel(
    savedStateHandle: SavedStateHandle,
    initialUiState: SampleUiState
) : SavedStateViewModel<SampleUiState>(savedStateHandle, initialUiState) {

    fun updateState(function: (SampleUiState) -> SampleUiState) {
        super.update(function)
    }

    fun updateStateAndSave(function: (SampleUiState) -> SampleUiState) {
        super.updateAndSave(function)
    }
}

package leegroup.app.kmm.gituser.ui.base

abstract class BaseDestination(val route: String = "") {

    open val deepLinks: List<String> = emptyList()

    open var destination: String = route

    data class Up(val results: HashMap<String, Any> = hashMapOf()) : BaseDestination() {

        fun addResult(key: String, value: Any) = apply {
            results[key] = value
        }
    }
}

package leegroup.app.kmm.gituser.domain.usecases.gituser

import kotlinx.coroutines.flow.flow
import leegroup.app.kmm.gituser.domain.repositories.GitUserDetailRepository

class GetGitUserDetailLocalUseCase  constructor(
    private val repository: GitUserDetailRepository
) {

    operator fun invoke(login: String) = flow {
        repository.getLocal(login)?.let {
            emit(it)
        }
    }
}

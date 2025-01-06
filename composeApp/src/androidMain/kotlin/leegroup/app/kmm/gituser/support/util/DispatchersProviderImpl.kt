package leegroup.app.kmm.gituser.support.util

import kotlinx.coroutines.Dispatchers

class DispatchersProviderImpl constructor() : DispatchersProvider {

    override val io = Dispatchers.IO

    override val main = Dispatchers.Main

    override val default = Dispatchers.Default
}

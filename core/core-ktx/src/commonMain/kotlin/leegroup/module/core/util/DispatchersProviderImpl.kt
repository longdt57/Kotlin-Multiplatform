package leegroup.module.core.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class DispatchersProviderImpl constructor() : DispatchersProvider {

    override val io = Dispatchers.IO

    override val main = Dispatchers.Main

    override val default = Dispatchers.Default
}

package com.alypplugin

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.NativeModule
import com.facebook.react.uimanager.ViewManager

class AlypPluginPackage : ReactPackage {

  override fun createNativeModules(rc: ReactApplicationContext): List<NativeModule> =
    listOf(AlypPluginModule(rc))

  override fun createViewManagers(rc: ReactApplicationContext): List<ViewManager<*, *>> =
    listOf(SecurePanelManager())   // register your View
}

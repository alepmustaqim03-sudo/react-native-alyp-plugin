package com.alypplugin

import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.bridge.UiThreadUtil
import com.alyp.securecore.ui.SecurePanelView
import com.facebook.react.uimanager.events.RCTEventEmitter

class SecurePanelManager : SimpleViewManager<SecurePanelView>() {
  override fun getName() = "SecurePanel"

  override fun createViewInstance(reactContext: ThemedReactContext): SecurePanelView {
    val view = SecurePanelView(reactContext)
    view.setOnAction {
      val event = com.facebook.react.bridge.WritableNativeMap()
      event.putString("type", "press")
      reactContext.getJSModule(RCTEventEmitter::class.java)
        .receiveEvent(view.id, "onSecureAction", event)
    }
    return view
  }

  @ReactProp(name = "title")
  fun setTitle(view: SecurePanelView, value: String?) {
    UiThreadUtil.runOnUiThread { view.setTitle(value) }
  }

  @ReactProp(name = "actionLabel")
  fun setActionLabel(view: SecurePanelView, value: String?) {
    UiThreadUtil.runOnUiThread { view.setActionLabel(value) }
  }
  override fun getExportedCustomBubblingEventTypeConstants(): MutableMap<String, Any> =
  mutableMapOf("onSecureAction" to mapOf(
    "phasedRegistrationNames" to mapOf("bubbled" to "onSecureAction")
  ))
}
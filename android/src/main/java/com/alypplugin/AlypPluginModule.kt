package com.alypplugin

import android.content.Intent
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.alyp.securecore.SecureCore

class AlypPluginModule(private val rc: ReactApplicationContext)
  : ReactContextBaseJavaModule(rc) {

  override fun getName() = "AlypPlugin"

  private val core: SecureCore = SecureCore.getInstance()

  @ReactMethod
  fun add(a: Int, b: Int, promise: Promise) = try {
    promise.resolve(core.add(a, b))
  } catch (e: Exception) { promise.reject("ERR_ADD", e) }

  @ReactMethod
  fun multiply(a: Double, b: Double, promise: Promise) = try {
    promise.resolve(core.multiply(a.toLong(), b.toLong()).toDouble())
  } catch (e: Exception) { promise.reject("ERR_MUL", e) }

  @ReactMethod
  fun hmacSign(base64Input: String, promise: Promise) = try {
    val data = android.util.Base64.decode(base64Input, android.util.Base64.NO_WRAP)
    val sig = core.hmacSign(data)
    promise.resolve(android.util.Base64.encodeToString(sig, android.util.Base64.NO_WRAP))
  } catch (e: Exception) { promise.reject("ERR_HMAC", e) }

  @ReactMethod
  fun showSecureScreen(promise: Promise) {
    try {
      val clazz = Class.forName("com.alyp.securecore.ui.SecureActivity")
      val intent = Intent(rc, clazz)

      val act = getCurrentActivity()  // <- explicit getter
      if (act != null) {
        act.startActivity(intent)
      } else {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        rc.startActivity(intent)      // ReactApplicationContext extends Context
      }
      promise.resolve(null)
    } catch (e: Exception) {
      promise.reject("ERR_SECURE_SCREEN", e)
    }
  }
}

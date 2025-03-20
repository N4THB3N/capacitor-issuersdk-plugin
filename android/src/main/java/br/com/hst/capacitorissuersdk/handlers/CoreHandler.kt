import com.getcapacitor.JSObject
import com.getcapacitor.PluginCall


object CoreHandler {
    fun handleSuccess(data: String, call: PluginCall) = try {
        call.resolve(JSObject(data))
    } catch (thr: Exception) {
        call.reject("Error when trying to convert to JSON")
    }

    fun handleError(call: PluginCall, result: String) = try {
        call.resolve(JSObject(result))
    } catch (th: Throwable) {
        call.reject("Error when trying to convert to JSON")
    }
}
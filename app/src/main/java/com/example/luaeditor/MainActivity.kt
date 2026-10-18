package com.example.luaeditor

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aloisknauss.ace.AceView
import com.aloisknauss.ace.editor.EditorMode
import com.aloisknauss.ace.editor.EditorTheme
import com.aloisknauss.ace.editor.AceConfig

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
   super.onCreate(savedInstanceState)

    val layout = android.widget.LinearLayout(this)
    layout.orientation = android.widget.LinearLayout.VERTICAL
    layout.layoutParams = android.view.ViewGroup.LayoutParams(
    android.view.ViewGroup.LayoutParams.MATCH_PARENT,
    android.view.ViewGroup.LayoutParams.MATCH_PARENT
  )

    // 1. Setup Ace Editor for Lua Code
    val editor = AceView(this)
    editor.setEditorMode(EditorMode.LUA)
    editor.setEditorTheme(EditorTheme.MONOKAI)
   editor.setEditorFontSize(14)

    // 2. Configure Autocomplete (Dictionary)
    val config = AceConfig.serializable {
    enableLiveAutocompletion = true
    liveAutocompleteDelay = 800

   setAcceptedCompleter {
    // Define the dictionary. Note: 'dict' is a custom Map for Ace.
    val dict = AceSuggestionTerm.PropertiesTerm(
    mutableMapOf(
    "firetouchinterest" to "firetouchinterest(
    instance: Instance, 
    position: Vector3? = nil, 
    hitRadius: float = 1,
    function: (TouchInterest) -> void
)",
    "spawn" to "spawn(function(): void)",
    "wait" to "wait(duration: number)",
    "workspace" to "workspace",
    "game" to "game"
   )
   )
    listOf(dict)
   }
   }
   editor.configure(config)

    // 3. Execute Injection Logic
   editor.addEventListener(com.aloisknauss.ace.editor.IZoneListener::class.java) {
    if (it.type == com.aloisknauss.ace.editor.ZoneChange.COMMIT) {
    val code = editor.getText()
    saveAndInject(code)
   }
  }

    layout.addView(editor)
  
    // Save Button
    val saveBtn = Button(this).apply {
    text = "Inject / Save"
    setOnClickListener { saveAndInject(editor.getText()) }
   }
   layout.addView(saveBtn)

    setContentView(layout)
  }

    private fun saveAndInject(code: String) {
    // Note: Roblox injection on Android usually requires ADB loopback 
    // or a root exploit like FEdit or KitExecutor logic.
  
    // Step A: Save locally for debugging
   try {
    val path = filesDir.absolutePath + "/roblox_inject.lua"
    java.io.File(path).writeText(code)
    Toast.makeText(this, "Saved to $path", Toast.LENGTH_SHORT).show()
  
    // OPTIONAL: If you have ADB connected to the same computer:
    // Runtime.getRuntime().exec("adb push $path /sdcard/Android/data/com.roblox/files/InjectedScript.lua")
  
    } catch (e: Exception) {
    Toast.makeText(this, "Save failed: ${e.message}", Toast.LENGTH_LONG).show()
   }
   }
}

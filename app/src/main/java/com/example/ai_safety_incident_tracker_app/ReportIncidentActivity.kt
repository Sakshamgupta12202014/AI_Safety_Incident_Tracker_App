package com.example.ai_safety_incident_tracker_app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.ai_safety_incident_tracker_app.Incident
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReportIncidentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_incident)

        val titleEdit = findViewById<EditText>(R.id.titleEditText)
        val descEdit = findViewById<EditText>(R.id.descriptionEditText)
        val severitySpinner = findViewById<Spinner>(R.id.severitySpinner)
        val saveButton = findViewById<Button>(R.id.saveButton)

        val options = listOf("Low", "Medium", "High")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, options)
        severitySpinner.adapter = spinnerAdapter
        severitySpinner.setSelection(1)

        saveButton.setOnClickListener {
            val title = titleEdit.text.toString()
            val description = descEdit.text.toString()
            val severity = severitySpinner.selectedItem.toString()
            if (title.isNotEmpty() && description.isNotEmpty()) {
                val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                val date = sdf.format(Date())
                val incident = Incident((0..99999).random(), title, description, severity, date)
                val intent = Intent()
                intent.putExtra("incident", incident)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}
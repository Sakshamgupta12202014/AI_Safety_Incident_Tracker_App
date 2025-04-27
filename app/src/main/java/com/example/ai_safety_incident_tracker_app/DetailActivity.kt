package com.example.ai_safety_incident_tracker_app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ai_safety_incident_tracker_app.Incident

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val incident = intent.getSerializableExtra("incident") as Incident
        findViewById<TextView>(R.id.titleText).text = incident.title
        findViewById<TextView>(R.id.severityText).text = incident.severity
        findViewById<TextView>(R.id.dateText).text = incident.reportedAt
        findViewById<TextView>(R.id.descriptionText).text = incident.description
        findViewById<Button>(R.id.backButton).setOnClickListener { finish() }
    }
}
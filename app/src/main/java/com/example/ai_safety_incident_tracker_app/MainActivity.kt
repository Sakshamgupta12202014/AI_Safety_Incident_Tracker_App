package com.example.ai_safety_incident_tracker_app

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ai_safety_incident_tracker_app.adapter.IncidentAdapter
import com.example.ai_safety_incident_tracker_app.Incident
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val incidents = mutableListOf(
        Incident(1, "Biased Recommendation Algorithm", "Algorithm consistently favored...", "Medium", "2025-03-15T10:00:00Z"),
        Incident(2, "Incorrect Image Tagging", "AI mislabels sensitive content", "High", "2025-04-01T09:30:00Z")
    )
    private val filteredIncidents = mutableListOf<Incident>()
    private lateinit var adapter: IncidentAdapter

    private val reportLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val newIncident = result.data?.getSerializableExtra("incident") as? Incident
            newIncident?.let {
                incidents.add(0, it)
                applyFilter(currentFilter)
            }
        }
    }

    private var currentFilter = "All"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val spinner = findViewById<Spinner>(R.id.severitySpinner)
        val fab = findViewById<FloatingActionButton>(R.id.fab)

        filteredIncidents.addAll(incidents)
        adapter = IncidentAdapter(filteredIncidents) { incident ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("incident", incident)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val options = listOf("All", "Low", "Medium", "High")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, options)
        spinner.adapter = spinnerAdapter
        spinner.setSelection(0)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View, position: Int, id: Long) {
                currentFilter = options[position]
                applyFilter(currentFilter)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        fab.setOnClickListener {
            val intent = Intent(this, ReportIncidentActivity::class.java)
            reportLauncher.launch(intent)
        }
    }

    private fun applyFilter(severity: String) {
        filteredIncidents.clear()
        if (severity == "All") filteredIncidents.addAll(incidents)
        else filteredIncidents.addAll(incidents.filter { it.severity == severity })
        adapter.notifyDataSetChanged()
    }
}
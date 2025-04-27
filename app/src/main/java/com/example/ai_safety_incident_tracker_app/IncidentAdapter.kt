package com.example.ai_safety_incident_tracker_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ai_safety_incident_tracker_app.Incident
import com.example.ai_safety_incident_tracker_app.R
//import com.example.ai_safety_incident_tracker_app.Incident

class IncidentAdapter(
    private val incidents: List<Incident>,
    private val onItemClick: (Incident) -> Unit
) : RecyclerView.Adapter<IncidentAdapter.IncidentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_incident, parent, false)
        return IncidentViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncidentViewHolder, position: Int) {
        holder.bind(incidents[position])
    }

    override fun getItemCount() = incidents.size

    inner class IncidentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(incident: Incident) {
            itemView.findViewById<TextView>(R.id.titleTextView).text = incident.title
            itemView.findViewById<TextView>(R.id.severityTextView).text = incident.severity
            itemView.findViewById<TextView>(R.id.dateTextView).text = incident.reportedAt
            itemView.setOnClickListener { onItemClick(incident) }
        }
    }
}
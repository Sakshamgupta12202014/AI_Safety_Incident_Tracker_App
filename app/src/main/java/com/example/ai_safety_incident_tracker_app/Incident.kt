package com.example.ai_safety_incident_tracker_app

import java.io.Serializable

data class Incident(
    val id: Int,
    val title: String,
    val description: String,
    val severity: String,
    val reportedAt: String
) : Serializable
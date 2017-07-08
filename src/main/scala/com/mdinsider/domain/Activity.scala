package com.mdinsider.domain

import java.time.Period

case class Activity(
  /* 1*/ customer_id: String,
  /* 2*/ agent: String,
  /* 3*/ doctor: String,
  /* 4*/ language: String,
  /* 5*/ os: String,
  /* 6*/ request_type: String,
  /* 7*/ search_term: String,
  /* 8*/ session_id: String,
  /* 9*/ specialty: String,
  /*10*/ success: String,
  /*11*/ url: String,
  /*12*/ user_id: String,
  /*13*/ user_ip: String,
  /*14*/ user_lat: String,
  /*15*/ user_long: String)


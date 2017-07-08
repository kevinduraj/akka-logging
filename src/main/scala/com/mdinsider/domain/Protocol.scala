package com.mdinsider.domain

object Protocol {

  case class CreateSchema()
  case class CreateTable()
  case class AddActivity(activity: Activity)

}

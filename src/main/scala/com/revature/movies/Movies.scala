package com.revature.movies

import java.sql.ResultSet

case class Movie(title: String)

object Movie {
    def produceResult(rs: ResultSet): Movie = apply(rs.getString("title"))
    }
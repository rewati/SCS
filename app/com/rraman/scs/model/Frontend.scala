package com.rraman.scs.model

import org.joda.time.DateTime

/**
  * Created by Rewati Raman(rewati.raman@gmail.com).
  */
class Frontend
case class Category(
                   subCategories: List[Category],
                   articles: List[Article],
                   pagelayout: PageLayout
                   )

case class Article (
                   category: Category,
                   tags: List[Tag],
                   publishDate: DateTime,
                   lastUpdateDate: DateTime,
                   keywords: List[String],
                   header: String,
                   content: String,
                   pageLayout: PageLayout,
                   comments: List[Comment]
                   )

case class Tag (
               name: String,
               description: String
               )

case class PageLayout (
                      name: String,
                      header: String,
                      head: String,
                      nav: String,
                      leftbar: String,
                      rightBasr: String,
                      footer: String,
                      content: String
                      )

case class Comment (
                   datePosted : DateTime,
                   comment: String,
                   user: User
                   )

case class User (
                username: String
                )

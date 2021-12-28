package bujjwole.tinnews.model

data class NewsResponse(val totalResults: Int,
                        val articles: List<Article>,
                        val code: String,
                        val message: String,
                        val status: String)
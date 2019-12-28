package com.hfrsoussama.projectplatine.feat.posts.core.model.extensions

import com.google.common.truth.Truth.*
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.AddressWs
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.CommentWs
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.GeoLocalisationWs
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.PostWs
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object ModelMappingSpec : Spek({

    describe("Address UI model object mapped from web service") {
        val addressWs by memoized {
            AddressWs(
                street = "down town",
                suite = "our neighberhood",
                city = "Los Gang",
                zipCode = "69",
                geo = GeoLocalisationWs(
                    lat = "999",
                    lng = "666"
                )
            )
        }

        it("must contain all web service model attributes") {
            val addressUi = addressWs.toUiModel()
            assertThat(addressUi.street).isEqualTo(addressWs.street)
            assertThat(addressUi.suite).isEqualTo(addressWs.suite)
            assertThat(addressUi.city).isEqualTo(addressWs.city)
            assertThat(addressUi.zipCode).isEqualTo(addressWs.zipCode)
            assertThat(addressUi.geo.lat).isEqualTo(addressWs.geo?.lat)
            assertThat(addressUi.geo.lng).isEqualTo(addressWs.geo?.lng)
        }
        it("must always have a geo location object") {
            val tempAddressWs = addressWs.copy(geo = null)
            val addressUi = tempAddressWs.toUiModel()
            assertThat(addressUi.geo).isNotNull()
            assertThat(addressUi.geo.lng).isEqualTo("")
            assertThat(addressUi.geo.lat).isEqualTo("")
        }
    }

    describe("Post model") {
        val postWs by memoized {
            PostWs(
                userId = 1L,
                id = 99L,
                title = "post title",
                body = "post body rock !"
            )
        }
        val postDb by memoized { postWs.toDbModel() }
        it("Database model must have all web service model attributes") {
            assertThat(postWs.id).isEqualTo(postDb.postId)
            assertThat(postWs.userId).isEqualTo(postDb.userWriterId)
            assertThat(postWs.title).isEqualTo(postDb.title)
            assertThat(postWs.body).isEqualTo(postDb.body)
        }
        it("UI model must have all database model attributes") {
            val postUi = toUiModel(postDb)
            assertThat(postUi.id).isEqualTo(postDb.postId)
            assertThat(postUi.userId).isEqualTo(postDb.userWriterId)
            assertThat(postUi.title).isEqualTo(postDb.title)
            assertThat(postUi.body).isEqualTo(postDb.body)
        }

    }

    describe("Comment model") {
        val commentWs by memoized {
            CommentWs(
                postId = 1L,
                id = 99L,
                name = "lesam",
                email = "bob@bob.com",
                body = "the actual comment body"
            )
        }
        val commentDb by memoized { commentWs.toDbModel() }
        it("Database model must have all web service model attributes") {
            assertThat(commentDb.postId).isEqualTo(commentWs.postId)
            assertThat(commentDb.commentId).isEqualTo(commentWs.id)
            assertThat(commentDb.authorName).isEqualTo(commentWs.name)
            assertThat(commentDb.authorEmail).isEqualTo(commentWs.email)
            assertThat(commentDb.body).isEqualTo(commentWs.body)
        }
        it("UI model must have all database model attributes") {
            val commentUi = toUiModel(commentDb)
            assertThat(commentUi.postId).isEqualTo(commentDb.postId)
            assertThat(commentUi.id).isEqualTo(commentDb.commentId)
            assertThat(commentUi.name).isEqualTo(commentDb.authorName)
            assertThat(commentUi.email).isEqualTo(commentWs.email)
            assertThat(commentUi.body).isEqualTo(commentWs.body)
        }
    }

})
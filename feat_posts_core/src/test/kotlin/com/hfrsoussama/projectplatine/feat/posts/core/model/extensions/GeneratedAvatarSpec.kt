package com.hfrsoussama.projectplatine.feat.posts.core.model.extensions

import com.google.common.truth.Truth.*
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.CommentUi
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.UserUi
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


object GeneratedAvatarSpec : Spek({

    describe("A generated Post author Avatar URL") {
        val user by memoized {
            UserUi(
                id = 1L,
                name = "bob",
                email = "bob@bob.com",
                username = "lesam"
            )
        }
        val generatedAvatarUrl by memoized { user.generateAvatarHttpUrl() }

        it("must use secure HTTP") {
            assertThat(generatedAvatarUrl.scheme())
                .matches("https")
        }

        it("must use adorable API") {
            assertThat(generatedAvatarUrl.host())
                .matches("api.adorable.io")
        }

        it("has a segment of image name with `.png` extension") {
            assertThat(
                generatedAvatarUrl.pathSegments().filter { it.endsWith(".png") }
            ).isNotEmpty()
        }

        describe("this image name") {

            val imageSegment by memoized {
                generatedAvatarUrl.pathSegments().firstOrNull { it.endsWith(".png") }
            }
            it("must start with the first two letters of the username of the user") {
                assertThat(imageSegment?.removeSuffix(".png"))
                    .startsWith(user.username.take(2))
            }
            it("must end with the last two letters of the name of the user") {
                assertThat(imageSegment?.removeSuffix(".png"))
                    .endsWith(user.name.takeLast(2))
            }
        }

    }

    describe("A generated Comment author Avatar Url") {
        val comment by memoized {
            CommentUi(
                postId = 1L,
                id = 1L,
                name = "bob",
                email = "bob@bob.com",
                body = "The actual comment body written by bob"
            )
        }
        val generatedAvatarUrl by memoized { comment.generateAvatarHttpUrl() }

        it("must use secure HTTP") {
            assertThat(generatedAvatarUrl.scheme())
                .matches("https")
        }
        it("muse use adorable API") {
            assertThat(generatedAvatarUrl.host())
                .matches("api.adorable.io")
        }
        it("has a segment of image name with `.png` extension") {
            assertThat(generatedAvatarUrl.pathSegments().filter { it.endsWith(".png") })
                .isNotEmpty()
        }


        describe("this image name") {

            val imageName by memoized {
                generatedAvatarUrl.pathSegments().firstOrNull { it.endsWith(".png") }
            }

            it("equivalent the first two lettres of the author name") {
                assertThat(imageName).startsWith(comment.name.take(2))
            }

        }

    }
})

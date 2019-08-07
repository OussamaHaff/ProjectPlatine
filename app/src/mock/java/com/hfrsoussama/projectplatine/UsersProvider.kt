package com.hfrsoussama.projectplatine

import com.hfrsoussama.projectplatine.model.Address
import com.hfrsoussama.projectplatine.model.Company
import com.hfrsoussama.projectplatine.model.GeoLocalisation
import com.hfrsoussama.projectplatine.model.User
import com.hfrsoussama.projectplatine.model.Post

object UsersProvider {
    fun getUsers() = listOf(
        User(
            id = 1,
            name = "Leanne Graham",
            username = "Bret",
            email = "Sincere@april.biz",
            address = Address(
                street = "Kulas Light",
                suite = "Apt. 556",
                city = "Gwenborough",
                zipCode = "92998-3874",
                geo = GeoLocalisation(
                    lat = "-37.3159",
                    lng = "81.1496"
                )
            ),
            phone = "1-770-736-8031 x56442",
            webSite = "hildegard.org",
            company = Company(
                name = "Romaguera-Crona",
                catchPhrase = "Multi-layered client-server neural-net",
                bs = "harness real-time e-markets"
            )
        ),
        User(
            id = 2,
            name = "Ervin Howell",
            username = "Antonette",
            email = "Shanna@melissa.tv",
            address = Address(
                street = "Victor Plains",
                suite = "Suite 879",
                city = "Wisokyburgh",
                zipCode = "90566-7771",
                geo = GeoLocalisation(
                    lat = "-43.9509",
                    lng = "-34.4618"
                )
            ),
            phone = "010-692-6593 x09125",
            webSite = "anastasia.net",
            company = Company(
                name = "Deckow-Crist",
                catchPhrase = "Proactive didactic contingency",
                bs = "synergize scalable supply-chains"
            )
        )
    )

    fun getAuthorOfPost(post: Post) = getUsers().filter { it.id == post.userId }[0]
}

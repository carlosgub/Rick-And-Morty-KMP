package org.carlosgub.yt.rick.and.morty.data.remote.responses

object MockResponses {
    val GET_CHARACTERS_RESPONSE = """
        {
          "info": { "count": 1, "pages": 1, "next": null, "prev": null },
          "results": [
            {
              "id": 1,
              "name": "Rick Sanchez",
              "status": "Alive",
              "species": "Human",
              "type": "",
              "gender": "Male",
              "origin": { "name": "Earth", "url": "" },
              "location": { "name": "Citadel", "url": "" },
              "image": "url",
              "episode": [],
              "url": "",
              "created": ""
            }
          ]
        }
    """.trimIndent()

    val GET_CHARACTER_RESPONSE = """
        {
          "id": 1,
          "name": "Rick Sanchez",
          "status": "Alive",
          "species": "Human",
          "type": "",
          "gender": "Male",
          "origin": { "name": "Earth", "url": "" },
          "location": { "name": "Citadel", "url": "" },
          "image": "url",
          "episode": [],
          "url": "",
          "created": ""
        }
    """.trimIndent()

    val GET_LOCATIONS_RESPONSE = """
        {
          "info": { "count": 1, "pages": 1, "next": null, "prev": null },
          "results": [
            {
              "id": 1,
              "name": "Earth",
              "type": "Planet",
              "dimension": "C-137"
            }
          ]
        }
    """.trimIndent()

    val GET_EPISODES_RESPONSE = """
        {
          "info": { "count": 1, "pages": 1, "next": null, "prev": null },
          "results": [
            {
              "id": 1,
              "name": "Pilot",
              "air_date": "December 2, 2013",
              "episode": "S01E01"
            }
          ]
        }
    """.trimIndent()
}

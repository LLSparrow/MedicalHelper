package com.oldsenior.ella.medicalhelper.listsample

object EventsFactory {
    fun getEvents(): List<EventItem> {
        val cards = ArrayList<EventItem>()
        cards.add(
            EventItem(
                "Andrei"
            )
        )
        cards.add(
            EventItem(
                "Anton"
            )
        )
        cards.add(
            EventItem(
                "Dmitry"
            )
        )
        cards.add(
            EventItem(
                "Daniil"
            )
        )

        return cards
    }

    fun getSecondEvents(): List<EventItem> {
        val cards = ArrayList<EventItem>()
        cards.add(
            EventItem(
                "Anna"
            )
        )
        cards.add(
            EventItem(
                "Kiril"
            )
        )
        cards.add(
            EventItem(
                "Andrei"
            )
        )
        cards.add(
            EventItem(
                "Sonya"
            )
        )

        return cards
    }

    fun getSecondList(): List<EventItem> {
        val cards = ArrayList<EventItem>()

        for (i in 1 until 100) {
            cards.add(EventItem(i.toString()))
        }

        return cards
    }
}
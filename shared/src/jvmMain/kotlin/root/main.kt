package root

import Molecule
import dsl.builder.column
import dsl.core.view.RootFusionView
import dsl.core.view.viewAttr.Gravity
import image.image
import rating.rating
import readScript
import shipping.shipping
import title.title
import price.price

private val state = title.view.state + shipping.view.state + image.view.state + rating.view.state + price.view.state

private val rootView = column {

    gravity { Gravity.LEFT }

    child {
        title.view.rootView
    }

    box {
        size {
            maxHeight { 8 }
        }
    }

    child {
        rating.view.rootView
    }

    box {
        size {
            maxHeight { 8 }
        }
    }

    child {
        image.view.rootView
    }

    box {
        size {
            maxHeight { 8 }
        }
    }

    child {
        price.view.rootView
    }

    box {
        size {
            maxHeight { 8 }
        }
    }

    child {
        shipping.view.rootView
    }
}


private val view = RootFusionView(rootView, state, readScript("title") + readScript("shipping"))

val root = Molecule("root", view)


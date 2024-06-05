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
import sku.sku

private val state = title.view.state + shipping.view.state + image.view.state + rating.view.state + price.view.state + sku.view.state

private val script = title.view.script + shipping.view.script + image.view.script + rating.view.script + price.view.script + sku.view.script

private val rootView = column {

    gravity { Gravity.LEFT }

    child {
        title.view.rootView
    }

    box {
        size {
            minHeight { 8 }
        }
    }

    child {
        rating.view.rootView
    }

    box {
        size {
            minHeight { 8 }
        }
    }

    child {
        image.view.rootView
    }

    box {
        size {
            minHeight{ 8 }
        }
    }

    child {
        sku.view.rootView
    }

    box {
        size {
            minHeight { 8 }
        }
    }

    child {
        price.view.rootView
    }

    box {
        size {
            minHeight { 8 }
        }
    }

    child {
        shipping.view.rootView
    }
}

private val view = RootFusionView(rootView, state, script)

val root = Molecule("root", view)


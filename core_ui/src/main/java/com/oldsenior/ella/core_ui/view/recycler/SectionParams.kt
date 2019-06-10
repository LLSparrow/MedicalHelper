package com.oldsenior.ella.core_ui.view.recycler

import androidx.annotation.LayoutRes

/**
 * Class used as constructor parameters of [Section].
 */
class SectionParams private constructor(builder: Builder) {
    @LayoutRes
    val itemResourceId: Int?
    @LayoutRes
    val headerResourceId: Int?
    @LayoutRes
    val footerResourceId: Int?
    @LayoutRes
    val loadingResourceId: Int?
    @LayoutRes
    val failedResourceId: Int?
    @LayoutRes
    val emptyResourceId: Int?

    val shouldFilterHeader: Boolean

    init {
        itemResourceId = builder.itemResourceId
        headerResourceId = builder.headerResourceId
        footerResourceId = builder.footerResourceId
        loadingResourceId = builder.loadingResourceId
        failedResourceId = builder.failedResourceId
        emptyResourceId = builder.emptyResourceId
        shouldFilterHeader = builder.shouldFilterHeader
    }

    class Builder {
        @LayoutRes
        var itemResourceId: Int? = null
            private set
        @LayoutRes
        var headerResourceId: Int? = null
            private set
        @LayoutRes
        var footerResourceId: Int? = null
            private set
        @LayoutRes
        var loadingResourceId: Int? = null
            private set
        @LayoutRes
        var failedResourceId: Int? = null
            private set
        @LayoutRes
        var emptyResourceId: Int? = null
            private set
        var shouldFilterHeader: Boolean = false
            private set

        /**
         * Set layout resource for Section's items.
         *
         * @param itemResourceId layout resource for Section's items
         * @return this builder
         */
        fun itemResourceId(@LayoutRes itemResourceId: Int): Builder {
            this.itemResourceId = itemResourceId

            return this
        }

        /**
         * Set layout resource for Section's header.
         *
         * @param headerResourceId layout resource for Section's header
         * @return this builder
         */
        fun headerResourceId(@LayoutRes headerResourceId: Int): Builder {
            this.headerResourceId = headerResourceId

            return this
        }

        /**
         * Set layout resource for Section's footer.
         *
         * @param footerResourceId layout resource for Section's footer
         * @return this builder
         */
        fun footerResourceId(@LayoutRes footerResourceId: Int): Builder {
            this.footerResourceId = footerResourceId
            return this
        }

        /**
         * Set layout resource for Section's loading state.
         *
         * @param loadingResourceId layout resource for Section's loading state
         * @return this builder
         */
        fun loadingResourceId(@LayoutRes loadingResourceId: Int): Builder {
            this.loadingResourceId = loadingResourceId
            return this
        }

        /**
         * Set layout resource for Section's failed state.
         *
         * @param failedResourceId layout resource for Section's failed state
         * @return this builder
         */
        fun failedResourceId(@LayoutRes failedResourceId: Int): Builder {
            this.failedResourceId = failedResourceId
            return this
        }

        /**
         * Set layout resource for Section's empty state.
         *
         * @param emptyResourceId layout resource for Section's empty state
         * @return this builder
         */
        fun emptyResourceId(@LayoutRes emptyResourceId: Int): Builder {
            this.emptyResourceId = emptyResourceId
            return this
        }


        /**
         *
         *
         * @param shouldFilterHeader
         * @return this builder
         */
        fun shouldFilterHeader(shouldFilterHeader:Boolean): Builder {
            this.shouldFilterHeader = shouldFilterHeader
            return this
        }

        /**
         * Build an instance of SectionParameters.
         *
         * @return an instance of SectionParameters
         */
        fun build(): SectionParams {
            return SectionParams(this)
        }
    }

    companion object {

        fun builder(): Builder {
            return Builder()
        }
    }
}
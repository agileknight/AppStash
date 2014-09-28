package io.github.appstash.shop.ui.page.test

import geb.Page

class ProductDetailPage extends Page {
    static at = { title == "Shop" }
    static content = {
        results(wait: true) { $("div.container div.col-md-8") }
        result { i -> results[i] }
        resultLink { i -> result(i).find("a")[0] }
        addToCartLink { resultLink(0) }
    }
}
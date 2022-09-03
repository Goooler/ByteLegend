/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bytelegend.client.app.ui.item

import com.bytelegend.app.client.ui.bootstrap.BootstrapListGroupItem
import com.bytelegend.client.app.engine.Item
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import csstype.ClassName
import kotlinx.js.jso
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.div
import react.react

class ItemsWidget : GameUIComponent<GameProps, State>() {
    override fun render() = Fragment.create {
        BootstrapListGroupItem {
            val items = game.heroPlayer.items
            onClick = {
                game.modalController.show {
                    child(
                        ItemModal::class.react,
                        jso {
                        this.game = props.game
                        this.title = "MyItems"
                        this.emptyText = "YouDontHaveAnyItems"
                    }
                    )
                }
            }
            div {
                className = ClassName("map-title-text items-widget")
                +"${i("Items")} (${items.size})"
            }
        }
    }
}

@Suppress("EXPERIMENTAL_API_USAGE")
class ItemModal : ItemOrAchievementModal() {
    override suspend fun loadItems(): Map<String, Item> = game.itemAchievementManager.getItems()
}

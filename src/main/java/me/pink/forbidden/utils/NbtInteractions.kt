package me.pink.forbidden.utils

import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun giveItemToPlayer(
    player: Player,
    material: Material,
    customName: String,
    key: String,
    value: String
): ItemStack {
    val item = createItemWithCustomNBT(material, customName, key, value)
    player.inventory.addItem(item)
    return item
}

fun createItemWithCustomNBT(
    material: Material,
    customName: String,
    key: String,
    value: String
): ItemStack {
    val item = ItemStack(material)
    val nbtI = NBTItem(item)

    nbtI.setString(key, value)
    nbtI.applyNBT(item)

    val meta = item.itemMeta ?: return item
    meta.setDisplayName(customName)

    item.itemMeta = meta
    return item
}
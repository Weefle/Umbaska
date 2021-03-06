package uk.co.umbaska.Misc;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import uk.co.umbaska.Enums.AnvilGUI_V1_8_R3;
import uk.co.umbaska.Enums.InventoryTypes;

/**
 * Created by Zachary on 3/30/2015.
 */
public class EffOpenInventory_V1_8_R3 extends Effect{

    private Expression<InventoryTypes> types;
    private Expression<String> name;
    private Expression<Player> player;

    @Override
    protected void execute(Event event){
        Player[] p = player.getAll(event);
        final String n = name.getSingle(event);
        InventoryTypes t2 = types.getSingle(event);
        InventoryType t = t2.getType();
        if (p == null) {
            return;
        }
        for (final Player pl : p){
            if (t != InventoryType.ANVIL) {
                Inventory inv = Bukkit.createInventory(null, t, n);

                pl.openInventory(inv);
            }else{
                AnvilGUI_V1_8_R3 anv = new AnvilGUI_V1_8_R3(pl, n, new AnvilGUI_V1_8_R3.AnvilClickEventHandler() {
                    @Override
                    public void onAnvilClick(AnvilGUI_V1_8_R3.AnvilClickEvent event) {
                        if(event.getSlot() == AnvilGUI_V1_8_R3.AnvilSlot.OUTPUT){
                            event.setWillClose(false);
                            event.setWillDestroy(false);

                        } else {
                            event.setWillClose(false);
                            event.setWillDestroy(false);
                        }
                    }
                });
                anv.open();
            }
        }
    }


    @Override
    public String toString(Event event, boolean b){
        return "Open Inventory";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
        types = (Expression<InventoryTypes>) expressions[0];
        name = (Expression<String>) expressions[1];
        player = (Expression<Player>) expressions[2];
        return true;
    }
}

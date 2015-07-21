package uk.co.umbaska.ArmorStands.Legs;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;

/**
 * Created by Zachary on 12/2/2014.
 */
public class ExprsRightLegDirectionX extends SimplePropertyExpression<Entity, Number> {
	@Override
	public Number convert(Entity ent) {
		if(ent == null)
			return null;
		return ((ArmorStand)ent).getRightLegPose().getX();
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		Entity ent = getExpr().getSingle(e); //Called to get the Target which is Player in this case.
		if(ent == null)
			return;
		if(ent.getType() != EntityType.ARMOR_STAND){
			return;
		}
		Number b = (Number) (delta[0]);
		if (mode == Changer.ChangeMode.SET){
            ArmorStand as = (ArmorStand) ent;
            ((ArmorStand)ent).setRightLegPose(as.getRightLegPose().setX(b.doubleValue()));
		}
        if (mode == Changer.ChangeMode.ADD){
            ArmorStand as = (ArmorStand) ent;
            ((ArmorStand)ent).setRightLegPose(as.getRightLegPose().setX(as.getRightLegPose().getX() + b.doubleValue()));
        }
        if (mode == Changer.ChangeMode.REMOVE){
            ArmorStand as = (ArmorStand) ent;
            ((ArmorStand)ent).setRightLegPose(as.getRightLegPose().setX(as.getRightLegPose().getX() - b.doubleValue()));
        }
	}


	@SuppressWarnings("unchecked")
	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET) //SET can be replaced with REMOVE ADD or similiar stuff.
			return CollectionUtils.array(Number.class); //The Class should be the TypeToGet and in this case Number.
        if (mode == Changer.ChangeMode.ADD)
            return CollectionUtils.array(Number.class);
		if (mode == Changer.ChangeMode.REMOVE)
			return CollectionUtils.array(Number.class);
		return null;
	}

	@Override
	public Class<? extends Number> getReturnType() { //ReturnType is TypeToGet and in this case Number.
		return Number.class;

	}
	@Override
	protected String getPropertyName() {
		// TODO Auto-generated method stub
		return "right leg angle X";
	}

}

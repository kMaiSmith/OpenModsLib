package openmods.network.events;

import java.io.DataInput;
import java.io.DataOutput;

import openmods.common.tileentity.OpenTileEntity;
import openmods.utils.ByteUtils;

public class PlayerMovementEvent extends TileEntityMessageEventPacket {

	public enum Type {
		JUMP,
		SNEAK
	}

	public PlayerMovementEvent() {}

	public PlayerMovementEvent(OpenTileEntity te, Type type) {
		super(te);
		this.type = type;
	}

	public Type type;

	@Override
	protected void readPayload(DataInput input) {
		int typeId = ByteUtils.readVLI(input);
		type = Type.values()[typeId];
	}

	@Override
	protected void writePayload(DataOutput output) {
		ByteUtils.writeVLI(output, type.ordinal());
	}

	@Override
	public EventType getType() {
		return EventType.PLAYER_MOVEMENT;
	}

}

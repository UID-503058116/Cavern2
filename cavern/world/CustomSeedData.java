package cavern.world;

import javax.annotation.Nullable;

import cavern.handler.CaveEventHooks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants.NBT;

public class CustomSeedData
{
	private Long seed;

	public CustomSeedData() {}

	public CustomSeedData(@Nullable NBTTagCompound nbt)
	{
		if (nbt != null && nbt.hasKey("Seed", NBT.TAG_ANY_NUMERIC))
		{
			seed = Long.valueOf(nbt.getLong("Seed"));
		}
	}

	public NBTTagCompound getCompound(@Nullable NBTTagCompound nbt)
	{
		if (seed == null)
		{
			return nbt;
		}

		if (nbt == null)
		{
			nbt = new NBTTagCompound();
		}

		nbt.setLong("Seed", seed.longValue());

		return nbt;
	}

	public long getSeed()
	{
		return getSeed(CaveEventHooks.RANDOM.nextLong());
	}

	public long getSeed(long customSeed)
	{
		if (seed == null)
		{
			setSeed(customSeed);
		}

		return seed.longValue();
	}

	public long getSeedValue()
	{
		return getSeedValue(0L);
	}

	public long getSeedValue(long defaultSeed)
	{
		return seed == null ? defaultSeed : seed.longValue();
	}

	public void setSeed(long newSeed)
	{
		seed = Long.valueOf(newSeed);
	}

	public void refreshSeed()
	{
		seed = null;

		getSeed();
	}
}
package cavern.world.gen;

import java.util.List;

import cavern.world.CaveBiomeProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerCaveBiomes extends GenLayer
{
	private final CaveBiomeProvider provider;

	public GenLayerCaveBiomes(CaveBiomeProvider provider, long seed, GenLayer layer)
	{
		this(provider, seed);
		this.parent = layer;
	}

	public GenLayerCaveBiomes(CaveBiomeProvider provider, long seed)
	{
		super(seed);
		this.provider = provider;
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth)
	{
		int dest[] = IntCache.getIntCache(width * depth);

		for (int dz = 0; dz < depth; dz++)
		{
			for (int dx = 0; dx < width; dx++)
			{
				initChunkSeed(dx + x, dz + z);

				dest[dx + dz * width] = Biome.getIdForBiome(getRandomBiome(provider.getCachedBiomes()));
			}
		}

		return dest;
	}

	private Biome getRandomBiome(List<Biome> biomes)
	{
		return biomes.get(nextInt(biomes.size()));
	}
}
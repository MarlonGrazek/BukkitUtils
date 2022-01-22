package com.marlongrazek.bukkitutils;

import org.bukkit.*;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.TropicalFish;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.map.MapView;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class ItemBuilder {

    private int amount;
    private String name;
    private Material material;
    private java.util.Map<Enchantment, Integer> enchantments = new HashMap<>();
    private List<String> lore = new ArrayList<>();
    private List<ItemFlag> itemFlags = new ArrayList<>();

    public ItemBuilder() {
    }

    public ItemBuilder(ItemStack itemStack) {
        ItemMeta meta;

        if (itemStack.getItemMeta() != null) {
            meta = itemStack.getItemMeta();
            this.name = meta.getDisplayName();
            this.material = itemStack.getType();
            this.enchantments = itemStack.getEnchantments();
            this.lore = meta.getLore();
            this.itemFlags = Arrays.asList(meta.getItemFlags().toArray(ItemFlag[]::new));
            this.amount = itemStack.getAmount();
        }
    }

    public ItemBuilder(String name) {
        this.name = name;
    }

    public ItemBuilder(Material material) {
        this.material = material;
    }

    public ItemBuilder(String name, Material material) {
        this.name = name;
        this.material = material;
    }

    public ItemStack toItemStack() {

        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;

        // axolotl bucket
        if (this instanceof AxolotlBucket) {
            AxolotlBucket item = (AxolotlBucket) this;
            AxolotlBucketMeta meta = (AxolotlBucketMeta) itemMeta;
            meta.setVariant(item.getVariant());
        }

        // banner
        else if (this instanceof Banner) {
            Banner item = (Banner) this;
            BannerMeta meta = (BannerMeta) itemMeta;
            meta.setPatterns(item.getPatterns());
        }

        // book
        else if (this instanceof Book) {
            Book item = (Book) this;
            BookMeta meta = (BookMeta) itemMeta;
            meta.setPages(item.getPages());
            meta.setTitle(item.getTitle());
            meta.setAuthor(item.getAuthor());
            meta.setGeneration(item.getGeneration());
        }

        // bundle
        else if (this instanceof Bundle) {
            Bundle item = (Bundle) this;
            BundleMeta meta = (BundleMeta) itemMeta;
            meta.setItems(item.getItems());
        }

        // compass
        else if (this instanceof Compass) {
            Compass item = (Compass) this;
            CompassMeta meta = (CompassMeta) itemMeta;
            meta.setLodestone(item.getLodestone());
            meta.setLodestoneTracked(item.isLodestoneTracked());
        }

        // crossbow
        else if (this instanceof Crossbow) {
            Crossbow item = (Crossbow) this;
            CrossbowMeta meta = (CrossbowMeta) itemMeta;
            meta.setChargedProjectiles(item.getChargedProjectiles());
        }

        // damageable
        else if (this instanceof Damageable) {
            Damageable item = (Damageable) this;
            org.bukkit.inventory.meta.Damageable meta = (org.bukkit.inventory.meta.Damageable) itemMeta;
            meta.setDamage(item.getDamage());
        }

        // stored enchantments
        else if (this instanceof EnchantmentStorage) {
            EnchantmentStorage item = (EnchantmentStorage) this;
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) itemMeta;
            item.getStoredEnchants().keySet().forEach(enchantment ->
                    meta.addEnchant(enchantment, item.getStoredEnchantLevel(enchantment), false));
        }

        // firework effect
        else if (this instanceof FireworkEffect) {
            FireworkEffect item = (FireworkEffect) this;
            FireworkEffectMeta meta = (FireworkEffectMeta) itemMeta;
            meta.setEffect(item.getEffect());
        }

        // firework
        else if (this instanceof Firework) {
            Firework item = (Firework) this;
            FireworkMeta meta = (FireworkMeta) itemMeta;
            meta.setPower(item.getPower());
            meta.addEffects(item.getEffects());
        }

        // knowledge book
        else if (this instanceof KnowledgeBook) {
            KnowledgeBook item = (KnowledgeBook) this;
            KnowledgeBookMeta meta = (KnowledgeBookMeta) itemMeta;
            meta.setRecipes(item.getRecipes());
        }

        // leather armor
        else if (this instanceof LeatherArmor) {
            LeatherArmor item = (LeatherArmor) this;
            LeatherArmorMeta meta = (LeatherArmorMeta) itemMeta;
            meta.setColor(item.getColor());
        }

        // map
        else if (this instanceof Map) {
            Map item = (Map) this;
            MapMeta meta = (MapMeta) itemMeta;
            meta.setColor(item.getColor());
            meta.setLocationName(item.getLocationName());
            meta.setMapView(item.getMapView());
            meta.setScaling(item.isScaling());
        }

        // potion
        else if (this instanceof Potion) {
            Potion item = (Potion) this;
            PotionMeta meta = (PotionMeta) itemMeta;
            meta.setBasePotionData(item.getBasePotionData());
            item.getCustomEffects().forEach(effect -> meta.addCustomEffect(effect, false));
            meta.setColor(item.getColor());
        }

        // repairable
        else if(this instanceof Repairable) {
            Repairable item = (Repairable) this;
            org.bukkit.inventory.meta.Repairable meta = (org.bukkit.inventory.meta.Repairable) itemMeta;
            meta.setRepairCost(item.getRepairCost());
        }

        // skull
        else if(this instanceof Skull) {
            Skull item = (Skull) this;
            SkullMeta meta = (SkullMeta) itemMeta;
            meta.setOwningPlayer(item.getOwningPlayer());
        }

        // suspicious stew
        else if(this instanceof SuspiciousStew) {
            SuspiciousStew item = (SuspiciousStew) this;
            SuspiciousStewMeta meta = (SuspiciousStewMeta) itemMeta;
            item.getCustomEffects().forEach(effect -> meta.addCustomEffect(effect, false));
        }

        // tropical fish bucket
        else if(this instanceof TropicalFishBucket) {
            TropicalFishBucket item = (TropicalFishBucket) this;
            TropicalFishBucketMeta meta = (TropicalFishBucketMeta) itemMeta;
            meta.setPattern(item.getPattern());
            meta.setPatternColor(item.getPatternColor());
            meta.setBodyColor(item.getBodyColor());
        }

        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemMeta.addItemFlags(itemFlags.toArray(ItemFlag[]::new));
        itemStack.setItemMeta(itemMeta);
        itemStack.addEnchantments(enchantments);

        return itemStack;
    }

    // setters
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addEnchantment(Enchantment enchantment, Integer level) {
        this.enchantments.put(enchantment, level);
    }

    public void setEnchantments(java.util.Map<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void addLoreLines(String... lines) {
        Collections.addAll(lore, lines);
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public void setLoreLines(int index, String line) {
        this.lore.set(index, line);
    }

    public void clearLore() {
        this.lore.clear();
    }

    public void addItemFlag(ItemFlag itemFlag) {
        if (!this.itemFlags.contains(itemFlag)) this.itemFlags.add(itemFlag);
    }

    public void setItemFlags(ItemFlag[] itemFlags) {
        this.itemFlags = Arrays.asList(itemFlags);
    }

    // getters
    public int getAmount() {
        return amount;
    }

    public List<ItemFlag> getItemFlags() {
        return itemFlags;
    }

    public List<String> getLore() {
        return lore;
    }

    public java.util.Map<Enchantment, Integer> getEnchantments() {
        return enchantments;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public static class AxolotlBucket extends ItemBuilder {

        private Axolotl.Variant variant;

        public AxolotlBucket() {
        }

        public AxolotlBucket(String name) {
            super.setName(name);
        }

        public AxolotlBucket(Material material) {
            super.setMaterial(material);
        }

        public AxolotlBucket(String name, Material material) {
            super.setName(name);
            super.setMaterial(material);
        }

        public AxolotlBucket(ItemStack itemStack) {
            AxolotlBucketMeta meta = (AxolotlBucketMeta) itemStack.getItemMeta();
            assert meta != null;
            this.variant = meta.getVariant();
        }

        public void setVariant(Axolotl.Variant variant) {
            this.variant = variant;
        }

        public Axolotl.Variant getVariant() {
            return variant;
        }
    }

    public static class Banner extends ItemBuilder {

        private List<Pattern> patterns = new ArrayList<>();

        public Banner() {
        }

        public Banner(String name) {
            super.setName(name);
        }

        public Banner(Material material) {
            super.setMaterial(material);
        }

        public Banner(String name, Material material) {
            super.setName(name);
            super.setMaterial(material);
        }

        public Banner(ItemStack itemStack) {
            BannerMeta meta = (BannerMeta) itemStack.getItemMeta();
            assert meta != null;
            this.patterns = meta.getPatterns();
        }

        public void setPattern(int index, Pattern pattern) {
            this.patterns.set(index, pattern);
        }

        public int numberOfPatterns() {
            return patterns.size();
        }

        public void setPatterns(List<Pattern> patterns) {
            this.patterns = patterns;
        }

        public void addPattern(Pattern pattern) {
            this.patterns.add(pattern);
        }

        public List<Pattern> getPatterns() {
            return patterns;
        }

        public Pattern getPattern(int index) {
            return this.patterns.get(index);
        }

        public void removePattern(int index) {
            this.patterns.remove(index);
        }
    }

    public static class Book extends ItemBuilder {

        private String title;
        private String author;
        private BookMeta.Generation generation;
        private List<String> pages = new ArrayList<>();

        public Book() {
        }

        public Book(String name) {
            super.setName(name);
        }

        public Book(Material material) {
            super.setMaterial(material);
        }

        public Book(String name, Material material) {
            super.setName(name);
            super.setMaterial(material);
        }

        public Book(ItemStack itemStack) {
            BookMeta meta = (BookMeta) itemStack.getItemMeta();
            assert meta != null;
            this.title = meta.getTitle();
            this.author = meta.getAuthor();
            this.generation = meta.getGeneration();
            this.pages = meta.getPages();
        }

        public boolean hasTitle() {
            return false;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean hasAuthor() {
            return author != null;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public boolean hasGeneration() {
            return generation != null;
        }

        public BookMeta.Generation getGeneration() {
            return generation;
        }

        public void setGeneration(BookMeta.Generation generation) {
            this.generation = generation;
        }

        public boolean hasPages() {
            return !this.pages.isEmpty();
        }

        public String getPage(int index) {
            return this.pages.get(index);
        }

        public void setPage(int index, String data) {
            this.pages.set(index, data);
        }

        public List<String> getPages() {
            return this.pages;
        }

        public void setPages(List<String> pages) {
            this.pages = pages;
        }

        public void setPages(String... pages) {
            Collections.addAll(this.pages, pages);
        }

        public void addPage(String... pages) {
            Collections.addAll(this.pages, pages);
        }

        public int getPageCount() {
            return this.pages.size();
        }
    }

    public static class Bundle extends ItemBuilder {

        private List<ItemStack> items = new ArrayList<>();

        public Bundle() {
        }

        public Bundle(String name) {
            super.setName(name);
        }

        public Bundle(Material material) {
            super.setMaterial(material);
        }

        public Bundle(String name, Material material) {
            super.setName(name);
            super.setMaterial(material);
        }

        public Bundle(ItemStack itemStack) {
            BundleMeta meta = (BundleMeta) itemStack.getItemMeta();
            assert meta != null;
            this.items = meta.getItems();
        }

        public boolean hasItems() {
            return !items.isEmpty();
        }

        public List<ItemStack> getItems() {
            return items;
        }

        public void setItems(List<ItemStack> items) {
            this.items = items;
        }

        public void addItem(ItemStack item) {
            this.items.add(item);
        }
    }

    public static class Compass extends ItemBuilder {

        private Location lodestone;
        private boolean tracked;

        public Compass() {
        }

        public Compass(String name) {
            super.setName(name);
        }

        public Compass(Material material) {
            super.setMaterial(material);
        }

        public Compass(String name, Material material) {
            super.setName(name);
            super.setMaterial(material);
        }

        public Compass(ItemStack itemStack) {
            CompassMeta meta = (CompassMeta) itemStack.getItemMeta();
            assert meta != null;
            this.lodestone = meta.getLodestone();
            this.tracked = meta.isLodestoneTracked();
        }

        public boolean hasLodestone() {
            return lodestone != null;
        }

        public Location getLodestone() {
            return lodestone;
        }

        public void setLodestone(Location lodestone) {
            this.lodestone = lodestone;
        }

        public boolean isLodestoneTracked() {
            return tracked;
        }

        public void setLodestoneTracked(boolean tracked) {
            this.tracked = tracked;
        }
    }

    public static class Crossbow extends ItemBuilder {

        List<ItemStack> projectiles = new ArrayList<>();

        public Crossbow() {
        }

        public Crossbow(String name) {
            super.setName(name);
        }

        public Crossbow(Material material) {
            super.setMaterial(material);
        }

        public Crossbow(String name, Material material) {
            super.setName(name);
            super.setMaterial(material);
        }

        public Crossbow(ItemStack itemStack) {
            CrossbowMeta meta = (CrossbowMeta) itemStack.getItemMeta();
            assert meta != null;
            this.projectiles = meta.getChargedProjectiles();
        }

        public boolean hasChargedProjectiles() {
            return !projectiles.isEmpty();
        }

        public List<ItemStack> getChargedProjectiles() {
            return projectiles;
        }

        public void setChargedProjectiles(List<ItemStack> projectiles) {
            this.projectiles = projectiles;
        }

        public void addChargedProjectile(ItemStack item) {
            this.projectiles.add(item);
        }
    }

    public static class Damageable extends ItemBuilder {

        private Integer damage;

        public Damageable() {
        }

        public Damageable(String name) {
            super.setName(name);
        }

        public Damageable(Material material) {
            super.setMaterial(material);
        }

        public Damageable(String name, Material material) {
            super.setName(name);
            super.setMaterial(material);
        }

        public Damageable(ItemStack itemStack) {
            org.bukkit.inventory.meta.Damageable meta = (org.bukkit.inventory.meta.Damageable) itemStack.getItemMeta();
            assert meta != null;
            this.damage = meta.getDamage();
        }

        public boolean hasDamage() {
            return damage != null;
        }

        public int getDamage() {
            return damage;
        }

        public void setDamage(int damage) {
            this.damage = damage;
        }
    }

    public static class EnchantmentStorage extends ItemBuilder {

        private java.util.Map<Enchantment, Integer> storedEnchantments = new HashMap<>();

        public EnchantmentStorage() {
        }

        public EnchantmentStorage(String name) {
            super.setName(name);
        }

        public EnchantmentStorage(Material material) {
            super.setMaterial(material);
        }

        public EnchantmentStorage(String name, Material material) {
            super.setName(name);
            super.setMaterial(material);
        }

        public EnchantmentStorage(ItemStack itemStack) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) itemStack.getItemMeta();
            assert meta != null;
            this.storedEnchantments = meta.getStoredEnchants();
        }

        public boolean hasStoredEnchants() {
            return !this.storedEnchantments.isEmpty();
        }

        public boolean hasStoredEnchant(Enchantment enchantment) {
            return this.storedEnchantments.containsKey(enchantment);
        }

        public int getStoredEnchantLevel(Enchantment enchantment) {
            return this.storedEnchantments.get(enchantment);
        }

        public java.util.Map<Enchantment, Integer> getStoredEnchants() {
            return this.storedEnchantments;
        }

        public void addStoredEnchant(Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
            this.storedEnchantments.put(enchantment, level);
        }

        public void removeStoredEnchant(Enchantment enchantment) throws IllegalArgumentException {
            this.storedEnchantments.remove(enchantment);
        }

        /*public boolean hasConflictingStoredEnchant(Enchantment enchantment) {
            return false;
        }*/
    }

    public static class FireworkEffect extends ItemBuilder {

        private org.bukkit.FireworkEffect effect;

        public FireworkEffect() {
        }

        public FireworkEffect(String name) {
            super.setName(name);
        }

        public FireworkEffect(Material material) {
            super.setMaterial(material);
        }

        public FireworkEffect(String name, Material material) {
            super.setName(name);
            super.setMaterial(material);
        }

        public FireworkEffect(ItemStack itemStack) {
            FireworkEffectMeta meta = (FireworkEffectMeta) itemStack.getItemMeta();
            assert meta != null;
            this.effect = meta.getEffect();
        }

        public void setEffect(org.bukkit.FireworkEffect effect) {
            this.effect = effect;
        }

        public boolean hasEffect() {
            return effect != null;
        }

        public org.bukkit.FireworkEffect getEffect() {
            return effect;
        }
    }

    public static class Firework extends ItemBuilder {

        private List<org.bukkit.FireworkEffect> effects = new ArrayList<>();
        private int power;

        public Firework() {
        }

        public Firework(String name) {
            super.setName(name);
        }

        public Firework(Material material) {
            super.setMaterial(material);
        }

        public Firework(String name, Material material) {
            super.setName(name);
            super.setMaterial(material);
        }

        public Firework(ItemStack itemStack) {
            FireworkMeta meta = (FireworkMeta) itemStack.getItemMeta();
            assert meta != null;
            this.effects = meta.getEffects();
            this.power = meta.getPower();
        }

        public void addEffect(org.bukkit.FireworkEffect effect) throws IllegalArgumentException {
            this.effects.add(effect);
        }

        public void addEffects(org.bukkit.FireworkEffect... effects) throws IllegalArgumentException {
            Collections.addAll(this.effects, effects);
        }

        public void addEffects(Iterable<org.bukkit.FireworkEffect> effects) throws IllegalArgumentException {
            effects.forEach(this.effects::add);
        }

        public List<org.bukkit.FireworkEffect> getEffects() {
            return effects;
        }

        public int getEffectsSize() {
            return effects.size();
        }

        public void removeEffect(int index) throws IndexOutOfBoundsException {
            this.effects.remove(index);
        }

        public void clearEffects() {
            this.effects.clear();
        }

        public boolean hasEffects() {
            return !this.effects.isEmpty();
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) throws IllegalArgumentException {
            this.power = power;
        }
    }

    public static class KnowledgeBook extends ItemBuilder {

        private List<NamespacedKey> recipes = new ArrayList<>();

        public KnowledgeBook() {
        }

        public KnowledgeBook(String name) {
            super.setName(name);
        }

        public KnowledgeBook(Material material) {
            super.setMaterial(material);
        }

        public KnowledgeBook(String name, Material material) {
            super.setName(name);
            super.setMaterial(material);
        }

        public KnowledgeBook(ItemStack itemStack) {
            KnowledgeBookMeta meta = (KnowledgeBookMeta) itemStack.getItemMeta();
            assert meta != null;
            this.recipes = meta.getRecipes();
        }

        public boolean hasRecipes() {
            return !recipes.isEmpty();
        }

        public List<NamespacedKey> getRecipes() {
            return recipes;
        }

        public void setRecipes(List<NamespacedKey> recipes) {
            this.recipes = recipes;
        }

        public void addRecipe(NamespacedKey... recipes) {
            Collections.addAll(this.recipes, recipes);
        }
    }

    public static class LeatherArmor extends ItemBuilder {

        private Color color;

        public LeatherArmor() {
        }

        public LeatherArmor(String name) {
            super.setName(name);
        }

        public LeatherArmor(Material material) {
            super.setMaterial(material);
        }

        public LeatherArmor(String name, Material material) {
            super.setName(name);
            super.setMaterial(material);
        }

        public LeatherArmor(ItemStack itemStack) {
            LeatherArmorMeta meta = (LeatherArmorMeta) itemStack.getItemMeta();
            assert meta != null;
            this.color = meta.getColor();
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }

    public static class Map extends ItemBuilder {

        private Color color;
        private String locationName;
        private MapView mapView;
        private boolean scaling;

        public Map() {
        }

        public Map(String name) {
            super.setName(name);
        }

        public Map(Material material) {
            super.setMaterial(material);
        }

        public Map(String name, Material material) {
            super.setName(name);
            super.setMaterial(material);
        }

        public Map(ItemStack itemStack) {
            MapMeta meta = (MapMeta) itemStack.getItemMeta();
            assert meta != null;
            this.color = meta.getColor();
            this.locationName = meta.getLocationName();
            this.mapView = meta.getMapView();
            this.scaling = meta.isScaling();
        }

        public boolean hasMapView() {
            return mapView != null;
        }

        public MapView getMapView() {
            return mapView;
        }

        public void setMapView(MapView map) {
            this.mapView = map;
        }

        public boolean isScaling() {
            return scaling;
        }

        public void setScaling(boolean value) {
            this.scaling = value;
        }

        public boolean hasLocationName() {
            return !this.locationName.isEmpty();
        }

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String name) {
            this.locationName = name;
        }

        public boolean hasColor() {
            return color != null;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }

    public static class Potion extends ItemBuilder {

        private PotionData potionData;
        private List<PotionEffect> effects = new ArrayList<>();
        private Color color;

        public Potion() {
        }

        public Potion(String name) {
            super.setName(name);
        }

        public Potion(Material material) {
            super.setMaterial(material);
        }

        public Potion(String name, Material material) {
            super.setName(name);
            super.setMaterial(material);
        }

        public Potion(ItemStack itemStack) {
            PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();
            assert potionMeta != null;
            this.potionData = potionMeta.getBasePotionData();
            this.effects = potionMeta.getCustomEffects();
            this.color = potionMeta.getColor();
        }

        public void setBasePotionData(PotionData data) {
            this.potionData = data;
        }

        public PotionData getBasePotionData() {
            return potionData;
        }

        public boolean hasCustomEffects() {
            return !effects.isEmpty();
        }

        public List<PotionEffect> getCustomEffects() {
            return effects;
        }

        public void addCustomEffect(PotionEffect effect, boolean overwrite) {
            this.effects.add(effect);
        }

        public void removeCustomEffect(PotionEffectType type) {
            effects.removeIf(effect -> effect.getType() == type);
        }

        public boolean hasCustomEffect(PotionEffectType type) {
            for (PotionEffect effect : effects) if (effect.getType() == type) return true;
            return false;
        }

        public void clearCustomEffects() {
            this.effects.clear();
        }

        public boolean hasColor() {
            return color != null;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }

    public static class Repairable extends ItemBuilder {

        private int repairCost;

        public Repairable() {
        }

        public Repairable(String name) {
            super.setName(name);
        }

        public Repairable(Material material) {
            super.setMaterial(material);
        }

        public Repairable(String name, Material material) {
            super.setName(name);
            super.setMaterial(material);
        }

        public Repairable(ItemStack itemStack) {
            org.bukkit.inventory.meta.Repairable repairable = (org.bukkit.inventory.meta.Repairable) itemStack.getItemMeta();
            assert repairable != null;
            this.repairCost = repairable.getRepairCost();
        }

        public boolean hasRepairCost() {
            return repairCost != 0;
        }

        public int getRepairCost() {
            return repairCost;
        }

        public void setRepairCost(int cost) {
            this.repairCost = cost;
        }
    }

    public static class Skull extends ItemBuilder {

        OfflinePlayer owningPlayer;

        public Skull() {
        }

        public Skull(String name) {
            super.setName(name);
        }

        public Skull(Material material) {
            super.setMaterial(material);
        }

        public Skull(String name, Material material) {
            super.setName(name);
            super.setMaterial(material);
        }

        public Skull(ItemStack itemStack) {
            SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
            assert meta != null;
            this.owningPlayer = meta.getOwningPlayer();
        }

        public boolean hasOwner() {
            return owningPlayer != null;
        }

        public OfflinePlayer getOwningPlayer() {
            return owningPlayer;
        }

        public void setOwningPlayer(OfflinePlayer owner) {
            this.owningPlayer = owner;
        }
    }

    public static class SuspiciousStew extends ItemBuilder {

        private List<PotionEffect> effects = new ArrayList<>();

        public SuspiciousStew() {
        }

        public SuspiciousStew(String name) {
            super.setName(name);
        }

        public SuspiciousStew(Material material) {
            super.setMaterial(material);
        }

        public SuspiciousStew(String name, Material material) {
            super.setName(name);
            super.setMaterial(material);
        }

        public SuspiciousStew(ItemStack itemStack) {
            SuspiciousStewMeta meta = (SuspiciousStewMeta) itemStack.getItemMeta();
            assert meta != null;
            this.effects = meta.getCustomEffects();
        }

        public boolean hasCustomEffects() {
            return !effects.isEmpty();
        }

        public List<PotionEffect> getCustomEffects() {
            return effects;
        }

        public void addCustomEffect(PotionEffect effect, boolean overwrite) {
            this.effects.add(effect);
        }

        public void removeCustomEffect(PotionEffectType type) {
            this.effects.removeIf(effect -> effect.getType() == type);
        }

        public boolean hasCustomEffect(PotionEffectType type) {
            for (PotionEffect effect : effects) if (effect.getType() == type) return true;
            return false;
        }

        public void clearCustomEffects() {
            this.effects.clear();
        }
    }

    public static class TropicalFishBucket extends ItemBuilder {

        private DyeColor patternColor;
        private DyeColor bodyColor;
        private TropicalFish.Pattern pattern;
        private boolean hasVariant = false;

        public TropicalFishBucket() {
        }

        public TropicalFishBucket(String name) {
            super.setName(name);
        }

        public TropicalFishBucket(Material material) {
            super.setMaterial(material);
        }

        public TropicalFishBucket(String name, Material material) {
            super.setName(name);
            super.setMaterial(material);
        }

        public TropicalFishBucket(ItemStack itemStack) {
            TropicalFishBucketMeta meta = (TropicalFishBucketMeta) itemStack.getItemMeta();
            assert meta != null;
            this.patternColor = meta.getPatternColor();
            this.bodyColor = meta.getBodyColor();
            this.pattern = meta.getPattern();
            this.hasVariant = meta.hasVariant();
        }

        public DyeColor getPatternColor() {
            return this.patternColor;
        }

        public void setPatternColor(DyeColor color) {
            this.patternColor = color;
        }

        public DyeColor getBodyColor() {
            return this.bodyColor;
        }

        public void setBodyColor(DyeColor color) {
            this.bodyColor = color;
        }

        public TropicalFish.Pattern getPattern() {
            return this.pattern;
        }

        public void setPattern(TropicalFish.Pattern pattern) {
            this.pattern = pattern;
        }

        public boolean hasVariant() {
            return hasVariant;
        }
    }
}

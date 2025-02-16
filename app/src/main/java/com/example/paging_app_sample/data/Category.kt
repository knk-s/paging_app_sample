package com.example.paging_app_sample.data

/*
  楽天レシピの大カテゴリ。今のところRICE_DISHES（ご飯もの）以外未使用。
*/
enum class LargeCategory(val id: String, val displayName: String){
    MEAT("10", "肉"),
    FISH("11", "魚"),
    VEGETABLES("12", "野菜"),
    OTHER_INGREDIENTS("13", "その他の食材"),
    RICE_DISHES("14", "ご飯もの"),
    PASTA("15", "パスタ"),
    NOODLES("16", "麺・粉物料理"),
    SOUP("17", "汁物・スープ"),
    SALAD("18", "サラダ"),
    SAUCE_SEASONING("19", "ソース・調味料・ドレッシング"),
    BENTO("20", "お弁当"),
    SWEETS("21", "お菓子"),
    BREAD("22", "パン"),
    HOT_POT("23", "鍋料理"),
    EVENTS("24", "行事・イベント"),
    WESTERN("25", "西洋料理"),
    OTHER_PURPOSES("26", "その他の目的・シーン"),
    BEVERAGES("27", "飲みもの"),
    POPULAR_MENU("30", "人気メニュー"),
    STANDARD_MEAT("31", "定番の肉料理"),
    STANDARD_FISH("32", "定番の魚料理"),
    EGG_DISHES("33", "卵料理"),
    FRUITS("34", "果物"),
    TOFU("35", "大豆・豆腐"),
    QUICK_EASY("36", "簡単料理・時短"),
    BUDGET_FRIENDLY("37", "節約料理"),
    DAILY_MENU("38", "今日の献立"),
    HEALTHY("39", "健康料理"),
    COOKING_TOOLS("40", "調理器具"),
    CHINESE("41", "中華料理"),
    KOREAN("42", "韓国料理"),
    ITALIAN("43", "イタリア料理"),
    FRENCH("44", "フランス料理"),
    ETHNIC("46", "エスニック料理・中南米"),
    OKINAWAN("47", "沖縄料理"),
    LOCAL_CUISINE("48", "日本各地の郷土料理"),
    OSECHI("49", "おせち料理"),
    CHRISTMAS("50", "クリスマス"),
    HINAMATSURI("51", "ひな祭り"),
    SPRING("52", "春（3月～5月）"),
    SUMMER("53", "夏（6月～8月）"),
    AUTUMN("54", "秋（9月～11月）"),
    WINTER("55", "冬（12月～2月）");
}

/*
  RICE_DISHES（ご飯もの）の中カテゴリ。
*/
enum class RiceDishMediumCategory(
    val id: String,
    val displayName: String,
) {
    SUSHI("129", "寿司"),
    DONBURI("130", "丼物"),
    FRIED_RICE("131", "チャーハン"),
    TAKIKOMI_RICE("132", "炊き込みご飯"),
    PORRIDGE("133", "おかゆ・雑炊類"),
    ONIGIRI("134", "おにぎり"),
    OMURICE("121", "オムライス"),
    CHICKEN_RICE("122", "チキンライス"),
    HASHED_BEEF_RICE("123", "ハヤシライス"),
    TACO_RICE("124", "タコライス"),
    LOCO_MOCO("125", "ロコモコ"),
    PAELLA("126", "パエリア"),
    PILAF("127", "ピラフ"),
    OTHER_RICE_DISHES("271", "その他のごはん料理"),
    HASHED_BEEF("368", "ハッシュドビーフ");

    companion object {
        fun fromOrdinal(ordinal: Int): RiceDishMediumCategory =
            entries.getOrNull(ordinal) ?: HASHED_BEEF

        fun getMaxOrdinal(): Int = entries.size - 1
    }
}
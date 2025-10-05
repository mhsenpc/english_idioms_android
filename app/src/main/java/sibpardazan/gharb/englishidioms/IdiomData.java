package sibpardazan.gharb.englishidioms;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class IdiomData {
    private static final String PREFS_NAME = "BookmarksPrefs";
    private static final String KEY_BOOKMARKED_IDS = "bookmarked_idiom_ids";
    private static Context appContext;

    public static void init(Context context) {
        appContext = context.getApplicationContext();
    }

    private static Set<Integer> getBookmarkedIdiomIds() {
        if (appContext == null) return new HashSet<>();

        SharedPreferences prefs = appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Set<String> bookmarkedStrings = prefs.getStringSet(KEY_BOOKMARKED_IDS, new HashSet<String>());
        Set<Integer> bookmarkedIds = new HashSet<>();

        for (String idStr : bookmarkedStrings) {
            bookmarkedIds.add(Integer.parseInt(idStr));
        }
        return bookmarkedIds;
    }

    private static void saveBookmarkedIdiomIds(Set<Integer> bookmarkedIds) {
        if (appContext == null) return;

        SharedPreferences prefs = appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Set<String> bookmarkedStrings = new HashSet<>();
        for (Integer id : bookmarkedIds) {
            bookmarkedStrings.add(id.toString());
        }

        editor.putStringSet(KEY_BOOKMARKED_IDS, bookmarkedStrings);
        editor.apply();
    }

    public static ArrayList<Idiom> getIdioms() {
        ArrayList<Idiom> idioms = new ArrayList<>();

        idioms.add(new Idiom(1,
            "Break a leg",
            "Good luck on your performance tonight! Break a leg!",
            "موفق باشی",
            "این اصطلاح برای آرزوی موفقیت بخصوص در اجراهای هنری مثل تئاتر، موسیقی و... استفاده می‌شود. ریشه این اصطلاح به این باور قدیمی برمی‌گردد که آرزوی مستقیم موفقیت باعث بدشانسی می‌شود، به همین جای آن از این اصطلاح عجیب استفاده می‌کنند."));

        idioms.add(new Idiom(2,
            "Hit the nail on the head",
            "You hit the nail on the head with that analysis.",
            "دقیقاً اشاره کردی",
            "وقتی کسی دقیقاً نکته اصلی را بیان می‌کند یا مشکل را به درستی تشخیص می‌دهد، از این اصطلاح استفاده می‌شود. مثل کسی که چکش را دقیقاً روی سر میخ می‌زند."));

        idioms.add(new Idiom(3,
            "Piece of cake",
            "The exam was a piece of cake.",
            "خیلی راحت بود",
            "وقتی کاری بسیار آسان و ساده باشد، از این اصطلاح استفاده می‌شود. مانند خوردن یک تکه کیک که نیازی به تلاش ندارد."));

        idioms.add(new Idiom(4,
            "Spill the beans",
            "Come on, spill the beans about what happened.",
            "راز را فاش کن",
            "وقتی کسی اطلاعات محرمانه یا رازی را که نباید می‌گفت، فاش می‌کند. مانند ریختن لوبیاها از کیسه که دیگر قابل جمع کردن نیستند."));

        idioms.add(new Idiom(5,
            "Burn the midnight oil",
            "I had to burn the midnight oil to finish the project.",
            "شب زنده داری کردم",
            "وقتی کسی تا دیروقت شب بیدار می‌ماند تا کار یا تحصیل کند. در قدیم با روغن نباتی (نفت) کار می‌کردند و شب زنده داری معادل سوزاندن روغن نیمه‌شب بود."));

        idioms.add(new Idiom(6,
            "Under the weather",
            "I'm feeling under the weather today.",
            "حالم خوب نیست",
            "وقتی کسی احساس بیماری یا ناخوشی می‌کند. این اصطلاح به معنای بیماری خفیف است و معمولاً برای بیماری‌های جدی استفاده نمی‌شود."));

        idioms.add(new Idiom(7,
            "Once in a blue moon",
            "I only see my cousins once in a blue moon.",
            "به ندرت",
            "وقتی اتفاقی خیلی به ندرت رخ می‌دهد. ماه آبی یک پدیده نادر نجومی است که تقریباً هر سه سال یک‌بار اتفاق می‌افتد."));

        idioms.add(new Idiom(8,
            "Costs an arm and a leg",
            "That new sports car costs an arm and a leg.",
            "خیلی گران است",
            "وقتی چیزی قیمت بسیار بالایی دارد. این اصطلاح برای تاکید بر گران بودن extreme استفاده می‌شود."));

        idioms.add(new Idiom(9,
            "A blessing in disguise",
            "Losing that job was a blessing in disguise.",
            "مصلحت در بی‌مصلحتی",
            "وقتی اتفاقی در ابتدا بد به نظر می‌رسد اما در نهایت منجر به نتیجه خوبی می‌شود."));

        idioms.add(new Idiom(10,
            "The ball is in your court",
            "I've done all I can. The ball is in your court now.",
            "نوبت توست",
            "وقتی تصمیم‌گیری یا اقدام بعدی به شخص دیگری مربوط است. این اصطلاح از بازی تنیس گرفته شده است."));

        idioms.add(new Idiom(11,
            "Break the ice",
            "He told a joke to break the ice.",
            "یخ جلسه را آب کردن",
            "وقتی در یک موقعیت اجتماعی جدید، کاری می‌کنید تا فضا صمیمی‌تر و راحت‌تر شود."));

        idioms.add(new Idiom(12,
            "Call it a day",
            "We've been working for 10 hours. Let's call it a day.",
            "کار را تمام کنیم",
            "وقتی تصمیم می‌گیرید برای آن روز کار را متوقف کرده و به خانه بروید."));

        idioms.add(new Idiom(13,
            "Cut corners",
            "Don't cut corners on safety procedures.",
            "کوتاهی کردن",
            "وقتی برای صرفه‌جویی در وقت یا پول، کیفیت کار را پایین می‌آورید."));

        idioms.add(new Idiom(14,
            "Get out of hand",
            "The party got out of hand.",
            "از کنترل خارج شدن",
            "وقتی موقعیتی کنترل‌ناپذیر می‌شود و از برنامه خارج پیش می‌رود."));

        idioms.add(new Idiom(15,
            "Make a long story short",
            "To make a long story short, we missed the flight.",
            "خلاصه بگویم",
            "وقتی می‌خواهید داستان طولانی را به صورت خلاصه و سریع تعریف کنید."));

        // Restore bookmarked state for each idiom
        Set<Integer> bookmarkedIds = getBookmarkedIdiomIds();
        for (Idiom idiom : idioms) {
            if (bookmarkedIds.contains(idiom.getId())) {
                idiom.setBookmarked(true);
            }
        }

        return idioms;
    }

    public static void setBookmarked(int idiomId, boolean bookmarked) {
        Set<Integer> bookmarkedIds = getBookmarkedIdiomIds();

        if (bookmarked) {
            bookmarkedIds.add(idiomId);
        } else {
            bookmarkedIds.remove(idiomId);
        }

        saveBookmarkedIdiomIds(bookmarkedIds);
    }

    public static boolean isBookmarked(int idiomId) {
        Set<Integer> bookmarkedIds = getBookmarkedIdiomIds();
        return bookmarkedIds.contains(idiomId);
    }
}
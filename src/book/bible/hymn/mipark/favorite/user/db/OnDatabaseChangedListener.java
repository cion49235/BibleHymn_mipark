package book.bible.hymn.mipark.favorite.user.db;

/**
 * Created by Daniel on 1/3/2015.
 * Listen for add/rename items in database
 */
public interface OnDatabaseChangedListener{
    void onNewDatabaseEntryAdded();
    void onDatabaseEntryRenamed();
}
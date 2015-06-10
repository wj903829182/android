package com.jack.litepaltest;

import java.util.Date;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SQLiteDatabase db = Connector.getDatabase();
		Button addNewButton = (Button) findViewById(R.id.addnews);
		addNewButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				 * News news=new News(); news.setTitle("这是一条新闻标题a");
				 * news.setContent("这是一条新闻内容aa"); news.setPublishDate(new
				 * Date()); //得到插入数据之前的id Log.d("TAG", "news id is " +
				 * news.getId()); if (news.save()) {
				 * Toast.makeText(MainActivity.this, "存储成功",
				 * Toast.LENGTH_SHORT).show(); } else {
				 * Toast.makeText(MainActivity.this, "存储失败",
				 * Toast.LENGTH_SHORT).show(); } //得到插入数据之后的id Log.d("TAG",
				 * "news id is " + news.getId());
				 */
				Comment comment1 = new Comment();
				comment1.setContent("好评！2");
				comment1.setPublishdate(new Date());
				;
				comment1.save();
				Comment comment2 = new Comment();
				comment2.setContent("赞一个2");
				comment2.setPublishdate(new Date());
				comment2.save();
				News news = new News();
				news.getCommentList().add(comment1);
				news.getCommentList().add(comment2);
				news.setTitle("第二条新闻标题");
				news.setContent("第二条新闻内容");
				news.setPublishDate(new Date());
				news.setCommentCount(news.getCommentList().size());
				news.save();
				/* DataSupport.saveAll(newsList); 存储一个集合 */

			}

		});

		Button updateNewButton = (Button) findViewById(R.id.updatenews);
		updateNewButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				 * DataSupport类中更新指定id值的方法 public static int update(Class<?>
				 * modelClass, ContentValues values, long id)
				 * 这个静态的update()方法接收三个参数，第一个参数是Class，传入我们要修改的那个类的Class就好，
				 * 第二个参数是ContentValues对象，这三个参数是一个指定的id，表示我们要修改哪一行数据。
				 */
				/*
				 * ContentValues values = new ContentValues();
				 * values.put("title", "今日iPhone6发布");
				 * DataSupport.update(News.class, values, 2);
				 */

				/*
				 * DataSupport类中更新有参数的方法 public static int updateAll(Class<?>
				 * modelClass, ContentValues values, String... conditions)
				 * updateAll()方法表示修改多行记录，其中第一个参数仍然是Class，第二个参数还是ContentValues对象，
				 * 第三个参数是一个conditions数组，用于指定修改哪些行的约束条件，返回值表示此次修改影响了多少行数据。
				 */
				/*
				 * ContentValues values2 = new ContentValues();
				 * values2.put("title", "今日iPhone6 Plus发布");
				 * DataSupport.updateAll(News.class, values2, "title=?",
				 * "今日iPhone6发布");
				 */

				/*
				 * 重点我们看一下最后的这个conditions数组，由于它的类型是一个String数组，
				 * 我们可以在这里填入任意多个String参数，其中最前面一个String参数用于指定约束条件，
				 * 后面所有的String参数用于填充约束条件中的占位符(即?号)，比如约束条件中有一个占位符，
				 * 那么后面就应该填写一个参数，如果有两个占位符，后面就应该填写两个参数，以此类推。
				 */
				ContentValues values = new ContentValues();
				values.put("title", "今日iPhone6发布");
				DataSupport.updateAll(News.class, values,
						"title = ? and commentcount > ?", "今日iPhone6 Plus发布",
						"0");

				/*
				 * 那么如果我们想把news表中所有新闻的标题都改成“今日iPhone6发布”，
				 * 该怎么写呢？其实这就更简单了，只需要把最后的约束条件去掉就行了，如下所示：
				 */
				/*
				 * ContentValues values = new ContentValues();
				 * values.put("title", "今日iPhone6 Plus发布");
				 * DataSupport.updateAll(News.class, values);
				 */
				/*
				 * 提供了一种不需要ContentValues就能修改数据的方法， 下面我们尝试使用这种新方法来完成上述同样的功能。
				 * 比如把news表中id为2的记录的标题改成“今日iPhone6发布”，就可以这样写：
				 */
				/*
				 * News updateNews = new News();
				 * updateNews.setTitle("今日iPhone6发布"); updateNews.update(2);
				 */

				/*
				 * 那么如果我们想把news表中标题为“今日iPhone6发布”且评论数量大于0的所有新闻的 标题改成“今日iPhone6
				 * Plus发布”，就可以这样写：
				 */
				/*
				 * News updateNews = new News();
				 * updateNews.setTitle("今日iPhone6发布");
				 * updateNews.updateAll("title = ? and commentcount > ?",
				 * "今日iPhone6发布", "0");
				 */

				/*
				 * 如果想要将某一列的数据修改成默认值的话，还需要借助setToDefault()方法。
				 * 把commentCount设置为默认值
				 */
				/*
				 * News updateNews = new News();
				 * updateNews.setToDefault("commentCount");
				 * updateNews.updateAll();
				 */
			}

		});

		Button deleteNewButton = (Button) findViewById(R.id.deletenews);
		deleteNewButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				 * 使用LitePal删除数据 public static int delete(Class<?> modelClass,
				 * long id) delete()方法接收两个参数，第一个参数是Class，传入我们要删除的那个类的Class就好，
				 * 第二个参数是一个指定的id，表示我们要删除哪一行数据。
				 */
				// 那么比如说我们想删除news表中id为2的记录，就可以这样写：
				/*
				 * DataSupport.delete(News.class, 2);
				 * 需要注意的是，这不仅仅会将news表中id为2的记录删除， 同时还会将其它表中以news
				 * id为2的这条记录作为外键的数据一起删除掉， 因为外键既然不存在了，那么这么数据也就没有保留的意义了。
				 */

				/*
				 * DataSupport中也提供了一个通过where语句来批量删除数据的方法 public static int
				 * deleteAll(Class<?> modelClass, String... conditions)
				 * deleteAll()方法接收两个参数，第一个参数是Class，传入我们要删除的那个类的Class就好，
				 * 第二个参数是一个conditions数组，用于指定删除哪些行的约束条件，
				 * 返回值表示此次删除了多少行数据，用法和updateAll()方法是基本相同的。
				 */
				// 比如说我们想把news表中标题为“今日iPhone6发布”且评论数等于0的所有新闻都删除掉，就可以这样写
				/*
				 * DataSupport.deleteAll(News.class,
				 * "title = ? and commentcount = ?", "今日iPhone6发布", "0");
				 */

				// 如果我们想把news表中所有的数据全部删除掉，就可以这样写：
				// DataSupport.deleteAll(News.class);在不指定约束条件的情况下，deleteAll()方法就会删除表中所有的数据了

				/*
				 * DataSupport类中提供了一个isSaved()方法，
				 * 这个方法返回true就表示该对象是经过持久化的，返回false则表示该对象未经过持久化
				 */

			}
		});

		// 进行查找
		Button findNews = (Button) findViewById(R.id.findnews);
		findNews.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 查询news表中id为1的这条记录，使用LitePal就可以这样写：
				// News news = DataSupport.find(News.class, 1);

				// 我们想要获取news表中的第一条数据，只需要这样写：
				// News firstNews = DataSupport.findFirst(News.class);

				// 如果是想要获取News表中的最后一条数据该怎么写呢？
				// News lastNews = DataSupport.findLast(News.class);

				// 我们想把news表中id为1、3、5、7的数据都查出来，该怎么写呢？
				// 只不过它可以指定多个id，并且返回值也不再是一个泛型类对象，而是一个泛型类集合，如下所示：
				// List<News> newsList = DataSupport.findAll(News.class, 1, 3,
				// 5, 7);
				/*
				 * long[] ids = new long[] { 1, 3, 5, 7 }; List<News> newsList =
				 * DataSupport.findAll(News.class, ids);
				 */

				// findAll()方法也是可以查询所有数据的，而且查询所有数据的写法更简单，只需要这样写：
				// List<News> allNews = DataSupport.findAll(News.class);
				// 在不指定具体id的情况下，findAll()方法查询出的就是news表中的所有数据了

				/*
				 * LitePal采用连缀查询,这种查询很灵活，可以根据我们实际的查询需求来动态配置查询参数。 那这里举个简单的例子，
				 * 比如我们想查询news表中所有评论数大于零的新闻，就可以这样写： List<News> newsList =
				 * DataSupport.where("commentcount > ?", "0").find(News.class);
				 * 首先是调用了DataSupport的where()方法，在这里指定了查询条件。where()方法接收任意个字符串参数，
				 * 其中第一个参数用于进行条件约束，从第二个参数开始，都是用于替换第一个参数中的占位符的。
				 * 那这个where()方法就对应了一条SQL语句中的where部分。
				 * 接着我们在where()方法之后直接连缀了一个find()方法，然后在这里指定一个泛型类，
				 * 表示用于查询哪张表。那么上面的一段代码，查询出的结果和如下SQL语句是相同的： select * from users
				 * where commentcount > 0;
				 * 
				 * 
				 * 但是这样会将news表中所有的列都查询出来，也许你并不需要那么多的数据，
				 * 而是只要title和content这两列数据。那么也很简单，我们只要再增加一个连缀就行了， 如下所示：
				 * List<News> newsList = DataSupport.select("title", "content")
				 * .where("commentcount > ?", "0").find(News.class);
				 * 这里我们新增了一个select()方法，这个方法接收任意个字符串参数，每个参数要求对应一个列名，
				 * 这样就只会把相应列的数据查询出来了，因此select()方法对应了一条SQL语句中的select部分。
				 * 那么上面的一段代码，查询出的结果和如下SQL语句是相同的： select title,content from users
				 * where commentcount > 0;
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 我希望将查询出的新闻按照发布的时间倒序排列，即最新发布的新闻放在最前面，那就可以这样写： List<News>
				 * newsList = DataSupport.select("title", "content")
				 * .where("commentcount > ?", "0")
				 * .order("publishdate desc").find(News.class);
				 * order()方法中接收一个字符串参数，用于指定查询出的结果按照哪一列进行排序，asc表示正序排序，
				 * desc表示倒序排序，因此order()方法对应了一条SQL语句中的order by部分。
				 * 那么上面的一段代码，查询出的结果和如下SQL语句是相同的: select title,content from users
				 * where commentcount > 0 order by publishdate desc;
				 * 
				 * 也许你并不希望将所有条件匹配的结果一次性全部查询出来，因为这样数据量可能会有点太大了，
				 * 而是希望只查询出前10条数据，那么使用连缀同样可以轻松解决这个问题，代码如下所示： List<News> newsList
				 * = DataSupport.select("title", "content")
				 * .where("commentcount > ?", "0")
				 * .order("publishdate desc").limit(10).find(News.class);
				 * 这里我们又连缀了一个limit()方法，这个方法接收一个整型参数，用于指定查询前几条数据，
				 * 这里指定成10，意思就是查询所有匹配结果中的前10条数据。 那么上面的一段代码，查询出的结果和如下SQL语句是相同的：
				 * select title,content from users where commentcount > 0 order
				 * by publishdate desc limit 10;
				 * 
				 * 
				 * 
				 * 刚才我们查询到的是所有匹配条件的前10条新闻，那么现在我想对新闻进行分页展示
				 * ，翻到第二页时，展示第11到第20条新闻，这又该怎么实现呢？没关系，在LitePal的帮助下，
				 * 这些功能都是十分简单的，只需要再连缀一个偏移量就可以了，如下所示： List<News> newsList =
				 * DataSupport.select("title", "content")
				 * .where("commentcount > ?", "0")
				 * .order("publishdate desc").limit(10).offset(10)
				 * .find(News.class); 这里我们又添加了一个offset()方法，用于指定查询结果的偏移量，这里指定成10，
				 * 就表示偏移十个位置，那么原来是查询前10条新闻的，偏移了十个位置之后，
				 * 就变成了查询第11到第20条新闻了，如果偏移量是20，那就表示查询第21到第30条新闻以此类推。
				 * 因此，limit()方法和order()方法共同对应了一条SQL语句中的limit部分。
				 * 那么上面的一段代码，查询出的结果和如下SQL语句是相同的： select title,content from users
				 * where commentcount > 0 order by publishdate desc limit 10,10;
				 */

				/*
				 * 激进查询 刚才我们所学的每一个类型的find()方法，都对应了一个带有isEager参数的方法重载，
				 * 这个参数相信大家一看就明白是什么意思了，设置成true就表示激进查询， 这样就会把关联表中的数据一起查询出来了。
				 * 比如说，我们想要查询news表中id为1的新闻，并且把这条新闻所对应的评论也一起查询出来， 就可以这样写： News
				 * news = DataSupport.find(News.class, 1, true); List<Comment>
				 * commentList = news.getCommentList();
				 * 激进查询的用法非常简单，就只有这么多，其它find()方法也都是同样的用法，
				 * 就不再重复介绍了。但是这种查询方式LitePal并不推荐，因为如果一旦关联表中的数据很多，
				 * 查询速度可能就会非常慢。而且激进查询只能查询出指定表的关联表数据，
				 * 但是没法继续迭代查询关联表的关联表数据。因此，这里我建议大家还是使用默认的懒加载更加合适，
				 * 至于如何查询出关联表中的数据，其实只需要在模型类中做一点小修改就可以了。修改News类中的代码，如下所示： public
				 * class News extends DataSupport{
				 * 
				 * ...
				 * 
				 * public List<Comment> getComments() { return
				 * DataSupport.where("news_id = ?",
				 * String.valueOf(id)).find(Comment.class); }
				 * 
				 * } 可以看到，我们在News类中添加了一个getComments()方法，
				 * 而这个方法的内部就是使用了一句连缀查询，查出了当前这条新闻对应的所有评论。
				 * 改成这种写法之后，我们就可以将关联表数据的查询延迟，当我们需要去获取新闻所对应的评论时，
				 * 再去调用News的getComments()方法，这时才会去查询关联数据。 这种写法会比激进查询更加高效也更加合理。
				 */

				/*
				 * 原生查询
				 * 
				 * 
				 * 
				 * DataSuppport类中还提供了一个findBySQL()方法，
				 * 使用这个方法就能通过原生的SQL语句方式来查询数据了，如下所示： Cursor cursor =
				 * DataSupport.findBySQL
				 * ("select * from news where commentcount>?", "0");
				 * 
				 * findBySQL()方法接收任意个字符串参数，其中第一个参数就是SQL语句，
				 * 后面的参数都是用于替换SQL语句中的占位符的，用法非常简单。另外，findBySQL()方法返回的
				 * 是一个Cursor对象，这和原生SQL语句的用法返回的结果也是相同的。
				 */

			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

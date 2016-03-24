package heiguang.com.mddemo.bean;

import java.util.List;

/**
 * Created by hiviiup on 16/3/24.
 */
public class NewDailys
{
    /**
     * date : 20160324
     * stories : [{"images":["http://pic2.zhimg.com/1ab94fbe5cc2522461d6307480f847a5.jpg"],"type":0,"id":8038006,"ga_prefix":"032411","title":"要接种计划外疫苗了，感觉有点犹豫"},{"images":["http://pic3.zhimg.com/4a43bdd67f41a333b348be3f06007302.jpg"],"type":0,"id":8044653,"ga_prefix":"032410","title":"这是第一次，NASA 拍到了超新星爆炸完整过程"},{"images":["http://pic2.zhimg.com/6815afee139bad00ab37670c1540e681.jpg"],"type":0,"id":8042451,"ga_prefix":"032409","title":"苹果的手机可能不酷了，但他们的工程师还有自我修养"},{"title":"「世界上有哪些惊艳港口」这种问题当然还得问海员","ga_prefix":"032408","images":["http://pic4.zhimg.com/1f2b76b4facf1130e556765458a8b733.jpg"],"multipic":true,"type":0,"id":8006603},{"images":["http://pic3.zhimg.com/dc566b1c8ea70cb035108984b89ea1ee.jpg"],"type":0,"id":8042544,"ga_prefix":"032407","title":"美国曾爆发麻疹，确实与反疫苗运动有些关系"},{"images":["http://pic1.zhimg.com/f5d6433e41a88803bb87d93e050c2564.jpg"],"type":0,"id":8035496,"ga_prefix":"032407","title":"炒房这么火，现在房子是不是已经变成金融工具了？"},{"images":["http://pic1.zhimg.com/9107adeac00838e74046b859c9a52c6c.jpg"],"type":0,"id":8032888,"ga_prefix":"032407","title":"中国游客真的像网传视频那样，在泰国疯狂抢虾吗？"},{"images":["http://pic1.zhimg.com/dc8be8d3e1038b7ab71063f70419e758.jpg"],"type":0,"id":8044260,"ga_prefix":"032407","title":"读读日报 24 小时热门 TOP 5 · 北方的孩子，不会懂我们的湿与远方"},{"images":["http://pic3.zhimg.com/a045b8098fb9b818fd4331c2b87a0d0a.jpg"],"type":0,"id":8025742,"ga_prefix":"032406","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic2.zhimg.com/322ee3c4cf3d94bdb2137fe7e70ffd71.jpg","type":0,"id":8044653,"ga_prefix":"032410","title":"这是第一次，NASA 拍到了超新星爆炸完整过程"},{"image":"http://pic3.zhimg.com/98bdc8af6a48a0b8698967ce6f0dcc16.jpg","type":0,"id":8032888,"ga_prefix":"032407","title":"中国游客真的像网传视频那样，在泰国疯狂抢虾吗？"},{"image":"http://pic3.zhimg.com/260b27fa7d404e19cd99620d67e631a2.jpg","type":0,"id":8037997,"ga_prefix":"032321","title":"当一个理性刻薄的君王有了个耽于玩乐的儿子"},{"image":"http://pic1.zhimg.com/2669de4e1d706a3fb9245c6794b26858.jpg","type":0,"id":8044260,"ga_prefix":"032407","title":"读读日报 24 小时热门 TOP 5 · 北方的孩子，不会懂我们的湿与远方"},{"image":"http://pic4.zhimg.com/83e037f8041c22993f90d93b5eb69a8b.jpg","type":0,"id":8042451,"ga_prefix":"032409","title":"苹果的手机可能不酷了，但他们的工程师还有自我修养"}]
     */

    private String date;
    /**
     * images : ["http://pic2.zhimg.com/1ab94fbe5cc2522461d6307480f847a5.jpg"]
     * type : 0
     * id : 8038006
     * ga_prefix : 032411
     * title : 要接种计划外疫苗了，感觉有点犹豫
     */

    private List<StoriesEntity> stories;
    /**
     * image : http://pic2.zhimg.com/322ee3c4cf3d94bdb2137fe7e70ffd71.jpg
     * type : 0
     * id : 8044653
     * ga_prefix : 032410
     * title : 这是第一次，NASA 拍到了超新星爆炸完整过程
     */

    private List<TopStoriesEntity> top_stories;

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public List<StoriesEntity> getStories()
    {
        return stories;
    }

    public void setStories(List<StoriesEntity> stories)
    {
        this.stories = stories;
    }

    public List<TopStoriesEntity> getTop_stories()
    {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesEntity> top_stories)
    {
        this.top_stories = top_stories;
    }

    public static class StoriesEntity
    {
        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType()
        {
            return type;
        }

        public void setType(int type)
        {
            this.type = type;
        }

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public String getGa_prefix()
        {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix)
        {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public List<String> getImages()
        {
            return images;
        }

        public void setImages(List<String> images)
        {
            this.images = images;
        }
    }

    public static class TopStoriesEntity
    {
        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage()
        {
            return image;
        }

        public void setImage(String image)
        {
            this.image = image;
        }

        public int getType()
        {
            return type;
        }

        public void setType(int type)
        {
            this.type = type;
        }

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public String getGa_prefix()
        {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix)
        {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }
    }
}

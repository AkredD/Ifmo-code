using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.ServiceModel.Description;
using System.Text;
using System.Threading.Tasks;

namespace musicApplication
{
    class RequestWCF
    {
        public static List<Item> openPLS(String path)
        {
            using (ChannelFactory<IService> cf = new ChannelFactory<IService>(new WebHttpBinding(), "http://localhost:8000/end"))
            {
                cf.Endpoint.Behaviors.Add(new WebHttpBehavior());
                IService channel = cf.CreateChannel();
                List<String> itemsS;
                itemsS = channel.EchoWithPost(path);
                List<Item> items = new List<Item>();
                for (int i = 0; i < itemsS.Count; i+=4){
                    items.Add(new Item(itemsS[i], itemsS[i + 1], itemsS[i + 2], itemsS[i+3]));
                }
                return items;
            }
        }
    }
}

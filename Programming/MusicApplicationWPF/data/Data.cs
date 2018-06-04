using MusicApplicationWPF;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace musicApplication
{

    class Data
    {
        private static Data instance;
        public List<Item> items;

        public MainWindow mainForm { get; set; }

        private Data()
        {
            items = new List<Item>();
        }

        public static Data getInstance()
        {
            if (instance == null){
                instance = new Data();
                return instance;
            }
            else
            {
                return instance;
            }
        }


        public List<Item> search(String part)
        {
            List<Item> local = new List<Item>();
            foreach(Item a in items)
            {
                String fullName = a.getSinger() + " - " + a.getName();
                String fullName2 = a.getSinger() + " " + a.getName();
                if ((part.Equals(fullName)) || (part.Equals(fullName2)) 
                    || (part.Equals(a.getName())) || (part.Equals(a.getSinger()))){
                    local.Add(a);
                }
            }
            return local;
        }

        public Boolean add(String singer, String name)
        {
            Item a = new Item(singer, name, "" , "100");
            return addItem(a);
        }

        public Boolean add(String singer, String name, String path)
        {
            Item a = new Item(singer, name, path, "100");
            return addItem(a);
        }

        private Boolean addItem(Item a)
        {
            if (items.Contains(a))
            {
                return false;
            }
            else
            {
                items.Add(a);
                mainForm.addElem(a.ToString());
                return true;
            }
        }

        public void openPLS(String path)
        {
            items = Manager.getInstance().openPLS(path);
            mainForm.setItems();
            //items = RequestWCF.openPLS(path);
        }

        public void savePLS(String path)
        {
            Manager.getInstance().savePLS(path);
            
        }

        public Boolean del(String fullName)
        {
            foreach(Item a in items)
            {
                String fullNameA = a.getSinger() + " - " + a.getName();
                String fullNameB = a.getSinger() + "-" + a.getName();
                if (fullName.Equals(fullNameA) || fullName.Equals(fullNameB))
                {
                    items.Remove(a);
                    //mainForm.removeElem(a.ToString());
                    return true;
                }
            }
            return false;
        }
    }
}

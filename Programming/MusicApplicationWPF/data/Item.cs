using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace musicApplication
{
    class Item
    {
        private String singer;
        private String name;
        private String path;
        private String length;

        public Item(String singer, String name, String path, String length)
        {
            this.singer = singer;
            this.name = name;
            this.path = path;
            this.length = length;
        }

        public override string ToString() { return singer + " - " + name; }

        public String getName() { return name; }

        public String getSinger() { return singer; }

        public String getPath() { return path; }

        public String getLength() { return length; }
    }
}

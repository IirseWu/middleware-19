//------------------------------------------------------------------------------
// <auto-generated>
//     此代码已从模板生成。
//
//     手动更改此文件可能导致应用程序出现意外的行为。
//     如果重新生成代码，将覆盖对此文件的手动更改。
// </auto-generated>
//------------------------------------------------------------------------------

namespace moocweb.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class qbase_set
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public qbase_set()
        {
            this.qbase_chapter = new HashSet<qbase_chapter>();
        }
    
        public long id { get; set; }
        public string set_name { get; set; }
        public bool isdelete { get; set; }
        public bool isopen { get; set; }
        public Nullable<long> admin_id { get; set; }
        public string describe { get; set; }
    
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<qbase_chapter> qbase_chapter { get; set; }
        public virtual teacher_info teacher_info { get; set; }
    }
}

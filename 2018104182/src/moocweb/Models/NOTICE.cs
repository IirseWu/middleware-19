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
    
    public partial class NOTICE
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public NOTICE()
        {
            this.notice_stu_class = new HashSet<notice_stu_class>();
        }
    
        public long Id { get; set; }
        public long tea_id { get; set; }
        public string content { get; set; }
        public short type { get; set; }
        public Nullable<System.DateTime> ntime { get; set; }
    
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<notice_stu_class> notice_stu_class { get; set; }
        public virtual teacher_info teacher_info { get; set; }
    }
}

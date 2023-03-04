package ph.kodego.alfaro.vismarjay.module_2.adapter

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ph.kodego.alfaro.vismarjay.module_2.R
import ph.kodego.alfaro.vismarjay.module_2.dao.StudentDAO
import ph.kodego.alfaro.vismarjay.module_2.dao.StudentDAOSQLImpl
import ph.kodego.alfaro.vismarjay.module_2.databinding.DialogueUpdateStudentBinding
import ph.kodego.alfaro.vismarjay.module_2.databinding.StudentItemBinding
import ph.kodego.alfaro.vismarjay.module_2.model.Student

class StudentAdapter (var students: ArrayList<Student>,var activity: Activity)
    : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>(), Filterable{

    var filteredStudents: List<Student> = ArrayList<Student>()
    var all_records = ArrayList<Student>()

    fun addStudent(student: Student){
        students.add(0,student)
        notifyItemInserted(0)
    }

    fun removeStudent(position: Int){
        students.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updateStudents(newStudents: ArrayList<Student>){
        students.clear()
        students.addAll(newStudents)
        notifyDataSetChanged()

    }

//    fun filterStudents(searchString: String){
//        var newSet = students.filter{it.lastName.contains(searchString)}
//        students.clear()
//        students.addAll(newSet)
////        students.filter{it.lastName.contains(searchString)}
//        notifyDataSetChanged()
//    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onCreateViewHolder(
        parent:ViewGroup,
        viewType:Int
    ):StudentAdapter.StudentViewHolder{

        //new
        all_records.clear()
        all_records.addAll(students)
        val itemBinding = StudentItemBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,false)
        return StudentViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder:StudentAdapter.StudentViewHolder,
    position:Int){
        holder.bindStudent(students[position],activity)
    }

    inner class StudentViewHolder(private val itemBinding:StudentItemBinding)
        :RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener{

        var student = Student()
        var context: Context? = null
        init {
            itemView.setOnClickListener(this)
        }

        fun bindStudent (student: Student, context: Context){
            this.student = student

            this.context = context
            itemBinding.studentName.text = "${student.lastName},${student.firstName}"
            itemBinding.btnDeleteRow.setOnClickListener{
                Snackbar.make(itemBinding.root,
                "Delete by button",
                Snackbar.LENGTH_SHORT
                ).show()

                var dao: StudentDAO = StudentDAOSQLImpl(activity.applicationContext)
                dao.deleteStudent(student.id)
                removeStudent(adapterPosition)
            }
            itemBinding.profilePicture.setImageResource(student.img)
        }

        override fun onClick(v: View?){
            Snackbar.make(itemBinding.root,
            "${student.lastName},${student.firstName}",
            Snackbar.LENGTH_SHORT
            ).show()

//            removeStudent(adapterPosition)

            customDialogue2()
        }

        fun showCustomDialogue(): Dialog {
            return activity?.let{
                val builder = AlertDialog.Builder(it)
                var dialogueUpdateStudentBinding : DialogueUpdateStudentBinding =
                    DialogueUpdateStudentBinding.inflate(it.layoutInflater)

                with(dialogueUpdateStudentBinding){
                    studentLastnameUpdate.setText(student.lastName)
                    studentFirstnameUpdate.setText(student.firstName)
                }

                with(builder){
                    setPositiveButton("Update",
                        DialogInterface.OnClickListener{dialog, id ->
                            var dao: StudentDAO = StudentDAOSQLImpl(activity.applicationContext)

                            student.lastName = dialogueUpdateStudentBinding.studentLastnameUpdate.text.toString()
                            student.firstName = dialogueUpdateStudentBinding.studentFirstnameUpdate.text.toString()
                            dao.updateStudent(student.id,student)

                            updateStudents(dao.getStudents())
                            notifyItemChanged(adapterPosition)
                        })
                    setNegativeButton("Cancel",
                        DialogInterface.OnClickListener{dialog, id ->

                        })
                    setView(dialogueUpdateStudentBinding.root)
                    create()
                }
            } ?: throw java.lang.IllegalStateException("Activity cannot be null")
        }

        fun customDialogue2(){
            var customDialogBinding: DialogueUpdateStudentBinding
            var builder = AlertDialog.Builder(activity)
            var inflater = activity.layoutInflater

            customDialogBinding = DialogueUpdateStudentBinding.inflate(inflater)

            customDialogBinding.btnUpdate.setOnClickListener{
                val updatedLastName = customDialogBinding.studentLastnameUpdate.text.toString()
                val updatedFirstName = customDialogBinding.studentFirstnameUpdate.text.toString()

                toast("$updatedLastName, $updatedFirstName")
            }

            val customDialogView = inflater.inflate(R.layout.dialogue_update_student,null)

            builder.setView(customDialogBinding.root)
            builder.create()
            builder.show()

        }


        fun showAlertDialogue(){
            val alertDialog = AlertDialog.Builder(activity)

            alertDialog.apply {
                setIcon(R.drawable.profileholder)
                setTitle("Hello")
                setMessage("I just wanted to greet you. I hope you are doing great!")
                setPositiveButton("Positive"){_, _ ->
                    toast("clicked positive button")
                }

                setNegativeButton("Negative"){_, _ ->
                    toast("clicked negative button")
                }

                setNeutralButton("Neutral"){_, _ ->
                    toast("clicked neutral   button")
                }
            }.create().show()
        }
    }

    private fun toast(text:String) = Toast.makeText(activity.applicationContext, text, Toast.LENGTH_SHORT).show()

    override fun getFilter(): Filter {
       return object : Filter(){
           override fun performFiltering(constraint: CharSequence?): FilterResults {
               val searchString = constraint.toString()

               if(searchString.trim().length == 0){
                   all_records
               }else{
                   filteredStudents = students.filter { it.lastName.contains(searchString) }
               }
               return FilterResults().apply{values = filteredStudents}
           }

           override fun publishResults(constraint: CharSequence?, result: FilterResults?) {
               students = if(result!!.values == null){
                   all_records
               }else{
                   result.values as ArrayList<Student>
               }

               notifyDataSetChanged()
           }
       }
    }

    fun showCustomDialogue(): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = activity.layoutInflater;

            builder.setView(inflater.inflate(R.layout.dialogue_update_student, null))
                .setPositiveButton("Update",
                    DialogInterface.OnClickListener { dialog, id ->
                        // sign in the user ...
                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
//                            getDialog().cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
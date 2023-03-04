package ph.kodego.alfaro.vismarjay.module_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import ph.kodego.alfaro.vismarjay.module_2.adapter.StudentAdapter
import ph.kodego.alfaro.vismarjay.module_2.dao.StudentDAO
import ph.kodego.alfaro.vismarjay.module_2.dao.StudentDAOSQLImpl
import ph.kodego.alfaro.vismarjay.module_2.databinding.ActivityMainBinding
import ph.kodego.alfaro.vismarjay.module_2.model.Student

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter
    private var students: ArrayList<Student> = ArrayList()
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var dao: StudentDAO
    private lateinit var searchView: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dao = StudentDAOSQLImpl(applicationContext)
        students = dao.getStudents()

//        init()

        studentAdapter = StudentAdapter(students, this)
        binding.list.layoutManager = LinearLayoutManager(applicationContext)
        //binding.list.layoutManager = GridLayoutManager(applicationContext,2)
        binding.list.adapter = studentAdapter

        binding.addStudentButton.setOnClickListener{
            val student = Student()

            student.firstName = binding.studentFirstname.text.toString()
            student.lastName = binding.studentLastname.text.toString()

            dao.addStudent(student)
            students = dao.getStudents()
            studentAdapter.updateStudents(students)

//            studentAdapter.addStudent(Student(
//                binding.studentFirstname.text.toString(),
//                binding.studentLastname.text.toString(),
        //        R.drawable.placeholder))
        }

        var swipeCallback = SwipeCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        swipeCallback.studentAdapter = studentAdapter
        itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(binding.list)

        //search
        binding.searchStudentRecords.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {

//                studentAdapter.filter.filter(newText)
                students = dao.searchStudentsByLastName(newText!!)
                studentAdapter.updateStudents(students)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {

                studentAdapter.filter.filter(query!!)
                return false
            }
        })

    }

    fun init(){
        students.add(Student("Vismar","Alfaro",R.drawable.book))
        students.add(Student("James","Tome",R.drawable.book))
        students.add(Student("Delight","Lumantas",R.drawable.book))
        students.add(Student("Nikko","Adaro",R.drawable.book))
        students.add(Student("Dave","Decano",R.drawable.book))
        students.add(Student("Rosito","Octura",R.drawable.book))
        students.add(Student("Via","Alfaro",R.drawable.book))
        students.add(Student("Berverly","Alfaro",R.drawable.book))
        students.add(Student("Sancho","Gallego",R.drawable.book))
        students.add(Student("Monkey","Luffy",R.drawable.book))

    }
}
package br.com.mathwidu.todoList.task;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mathwidu.todoList.utils.Utils;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @RequestMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){ {
        var idUser =request.getAttribute("idUser");
        taskModel.setUserId((UUID) idUser);

        var currentDate = LocalDate.now();
        if(currentDate.isAfter(taskModel.getStartAt())){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de inicio não pode ser menor que a data atual");
            
        }
        if(currentDate.isAfter(taskModel.getEndAt())){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de fim não pode ser menor que a data atual");
            
        }

        if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de inicio não pode ser maior que a data de fim");
            
        }
        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }
}
    
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request){
        var idUser =request.getAttribute("idUser");
        var tasks = this.taskRepository.findByUserId((UUID) idUser);
        return tasks;
    }

    @PutMapping("/{id}")
    public ResponseEntity updata(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id){
            var task = this.taskRepository.findById(id).orElse(null);

            if(task == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa não encontrada");
            }

            var idUser =request.getAttribute("idUser");

            if (!task.getUserId().equals(idUser)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario não autorizado");
            }

            Utils.copyNonNullPropertis(taskModel, task);
            var taskUpdated = this.taskRepository.save(task);
            return ResponseEntity.status(HttpStatus.OK).body(this.taskRepository.save(task));

    }
}
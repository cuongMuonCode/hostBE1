package com.example.LTNC_WEB_1.classRoom;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface classRoomRepository extends MongoRepository<classRoom,String> {
    public classRoom findClassRoomByClassIdAndCourseId(String classId,String courseId);
    public void deleteClassRoomByClassId(String classId);
    public classRoom findClassRoomByClassId(String classId);
}

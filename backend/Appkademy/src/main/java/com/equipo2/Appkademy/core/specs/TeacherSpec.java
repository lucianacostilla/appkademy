
package com.equipo2.Appkademy.core.specs;

import com.equipo2.Appkademy.core.model.entity.ScheduledAppointment;
import com.equipo2.Appkademy.core.model.entity.ScheduledAppointment_;
import com.equipo2.Appkademy.core.model.entity.Teacher;
import com.equipo2.Appkademy.core.model.enums.City;
import com.equipo2.Appkademy.core.model.enums.Country;
import com.equipo2.Appkademy.core.model.enums.Province;
import com.equipo2.Appkademy.core.model.enums.TeachingMasteryLevel;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

public class TeacherSpec {

    public static Specification<Teacher> teacherIdsIn(List<Long> teacherIds) {
        return (root, query, cb) -> {
            return cb.and(root.get("id").in(teacherIds));
        };
    }

    public static Specification<Teacher> countryEquals(Country country) {
        return (root, query, cb) -> {
            return cb.equal(root.get("address").get("country"), country);
        };
    }

    public static Specification<Teacher> provinceEquals(Province province) {
        return (root, query, cb) -> {
            return cb.equal(root.get("address").get("province"), province);
        };
    }
    public static Specification<Teacher> cityEquals(City city) {
        return (root, query, cb) -> {
            return cb.equal(root.get("address").get("city"), city);
        };
    }

    public static Specification<Teacher> proficiencySubject(String subject) {
        return (root, query, cb) -> {
            return cb.equal(root.join("proficiencies").get("subject").get("name"), subject);
        };
    }

    public static Specification<Teacher> proficiencyMasteryLevel(TeachingMasteryLevel masteryLevel) {
        return (root, query, cb) -> {
            return cb.equal(root.join("proficiencies").get("masteryLevel"), masteryLevel);
        };
    }

    public static Specification<Teacher> teacherWithoutScheduledAppointmentAtStartTime(LocalDateTime specificStartTime) {
        return (root, query, criteriaBuilder) -> {
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<ScheduledAppointment> subqueryRoot = subquery.from(ScheduledAppointment.class);
            subquery.select(subqueryRoot.get(ScheduledAppointment_.teacherId))
                    .where(criteriaBuilder.equal(subqueryRoot.get(ScheduledAppointment_.startsOn), specificStartTime));
            return criteriaBuilder.not(root.get("id").in(subquery));
        };
    }




}





/*
 * Copyright (c) 2024 Give It A Bash
 *
 * This file is part of Give It A Bash proprietary software.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 * Proprietary and confidential.
 *
 * Created and maintained by Give It A Bash.
 */

package com.give_it_a_bash.application_programming_interface.testData;

import com.give_it_a_bash.application_programming_interface.entities.*;
import lombok.Getter;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * A helper class that provides static methods to create fully constructed test data
 * for various entities used in unit tests.
 */
public final class TestDataHelper {

    @Getter
    private static Facility FACILITY = createFacility("Training Room", FacilityType.CLASSROOM,
            "A room equipped for training and practice.", true,
            "Main Building, 2nd Floor", 30, true);

    @Getter
    private static Lesson LESSON = createLesson(LocalDateTime.of(2024, 11, 1, 10, 0),
            LocalDateTime.of(2024, 11, 1, 11, 30));

    @Getter
    private static Power POWER = createPower("Telekinesis", 10,
            "Enables the user to mentally manipulate and move objects without physical contact.",
            "Psychic", true, 10, PowerSource.GENETIC_MUTATION);

    @Getter
    private static Subject SUBJECT = createSubject("Psychic Studies");

    @Getter
    private static Teacher TEACHER = createTeacher("Charles", "Xavier",
            "Professor X", "professor.xavier@example.com", "+1-555-0303",
            "1407 Graymalkin Lane, Salem Center, NY", "PhD in Genetics, Mutant Studies",
            20, "Psychic Studies", true,
            Collections.singletonList("Formed the X-Men"));

    @Getter
    private static SchoolData SCHOOL_DATA = createSchoolData("Xavier Institute for Higher Learning",
            "1407 Graymalkin Lane, Salem Center, NY", "Mutatis Mutandis",
            1963, "Mutant Education and Research", "+1-555-XAVIER",
            true);

    @Getter
    private static Achievement ACHIEVEMENT = createAchievement("Outstanding Contribution",
            "Awarded for innovative contributions to the annual school science fair, showcasing practical applications of scientific concepts.",
            LocalDate.of(2021, 5, 21), "Professor Hank McCoy",
            SubjectCategory.BIOLOGY);

    @Getter
    private static Student STUDENT = createStudent("Jean", "Grey", "Phoenix",
            "John", "Grey", "+1-555-0101",
            "john.grey@example.com", "+1-555-0202", "jean.grey@example.com",
            true, Status.ACTIVE, Collections.singletonList("Mission X"));

    static {
        ACHIEVEMENT.setStudent(STUDENT);

        FACILITY.setSchoolData(SCHOOL_DATA);

        LESSON.setSubject(SUBJECT);
        LESSON.setTeacher(TEACHER);
        LESSON.setStudents(Collections.singletonList(STUDENT));

        SUBJECT.setLessons(Collections.singletonList(LESSON));
        SUBJECT.setSchoolData(SCHOOL_DATA);
        SUBJECT.setTeacher(Collections.singletonList(TEACHER));

        SCHOOL_DATA.setStudents(Collections.singletonList(STUDENT));
        SCHOOL_DATA.setTeachers(Collections.singletonList(TEACHER));
        SCHOOL_DATA.setFacilities(Collections.singletonList(FACILITY));
        SCHOOL_DATA.setSubjects(Collections.singletonList(SUBJECT));

        STUDENT.setSchoolData(SCHOOL_DATA);
        STUDENT.setPower(POWER);
        STUDENT.setLessons(Collections.singletonList(LESSON));
        STUDENT.setAchievements(Collections.singletonList(ACHIEVEMENT));
    }

    private TestDataHelper() {
        // Private constructor to prevent instantiation
    }

    public static void reset() {
        try {
            // Iterate through all declared fields in the class
            for (Field field : TestDataHelper.class.getDeclaredFields()) {
                if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);

                    // Reinitialize static fields
                    switch (field.getName()) {
                        case "FACILITY":
                            field.set(null, createFacility("Training Room", FacilityType.CLASSROOM,
                                    "A room equipped for training and practice.", true,
                                    "Main Building, 2nd Floor", 30, true));
                            break;
                        case "LESSON":
                            field.set(null, createLesson(LocalDateTime.of(2024, 11, 1, 10, 0),
                                    LocalDateTime.of(2024, 11, 1, 11, 30)));
                            break;
                        case "POWER":
                            field.set(null, createPower("Telekinesis", 10,
                                    "Enables the user to mentally manipulate and move objects without physical contact.",
                                    "Psychic", true, 10, PowerSource.GENETIC_MUTATION));
                            break;
                        case "SUBJECT":
                            field.set(null, createSubject("Psychic Studies"));
                            break;
                        case "TEACHER":
                            field.set(null, createTeacher("Charles", "Xavier",
                                    "Professor X", "professor.xavier@example.com", "+1-555-0303",
                                    "1407 Graymalkin Lane, Salem Center, NY", "PhD in Genetics, Mutant Studies",
                                    20, "Psychic Studies", true,
                                    Collections.singletonList("Formed the X-Men")));
                            break;
                        case "SCHOOL_DATA":
                            field.set(null, createSchoolData("Xavier Institute for Higher Learning",
                                    "1407 Graymalkin Lane, Salem Center, NY", "Mutatis Mutandis",
                                    1963, "Mutant Education and Research", "+1-555-XAVIER",
                                    true));
                            break;
                        case "ACHIEVEMENT":
                            field.set(null, createAchievement("Outstanding Contribution",
                                    "Awarded for innovative contributions to the annual school science fair, showcasing practical applications of scientific concepts.",
                                    LocalDate.of(2021, 5, 21), "Professor Hank McCoy",
                                    SubjectCategory.BIOLOGY));
                            break;
                        case "STUDENT":
                            field.set(null, createStudent("Jean", "Grey", "Phoenix",
                                    "John", "Grey", "+1-555-0101",
                                    "john.grey@example.com", "+1-555-0202", "jean.grey@example.com",
                                    true, Status.ACTIVE, Collections.singletonList("Mission X")));
                            break;
                        default:
                            throw new UnsupportedOperationException("Unrecognized field: " + field.getName());
                    }
                }
            }

            ACHIEVEMENT.setStudent(STUDENT);

            FACILITY.setSchoolData(SCHOOL_DATA);

            LESSON.setSubject(SUBJECT);
            LESSON.setTeacher(TEACHER);
            LESSON.setStudents(Collections.singletonList(STUDENT));

            SUBJECT.setLessons(Collections.singletonList(LESSON));
            SUBJECT.setSchoolData(SCHOOL_DATA);
            SUBJECT.setTeacher(Collections.singletonList(TEACHER));

            SCHOOL_DATA.setStudents(Collections.singletonList(STUDENT));
            SCHOOL_DATA.setTeachers(Collections.singletonList(TEACHER));
            SCHOOL_DATA.setFacilities(Collections.singletonList(FACILITY));
            SCHOOL_DATA.setSubjects(Collections.singletonList(SUBJECT));

            STUDENT.setSchoolData(SCHOOL_DATA);
            STUDENT.setPower(POWER);
            STUDENT.setLessons(Collections.singletonList(LESSON));
            STUDENT.setAchievements(Collections.singletonList(ACHIEVEMENT));

        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to reset TestDataHelper", e);
        }
    }

    /**
     * Creates a new Facility instance with the specified attributes.
     *
     * @param name                 the name of the facility
     * @param type                 the type of the facility
     * @param description          a description of the facility
     * @param isAccessible         indicates if the facility is accessible
     * @param locationWithinCampus the location of the facility within the campus
     * @param capacity             the capacity of the facility
     * @param isOperational        indicates if the facility is operational
     * @return a fully constructed Facility instance
     */
    public static Facility createFacility(String name, FacilityType type, String description, boolean isAccessible,
                                          String locationWithinCampus, int capacity, boolean isOperational) {
        return Facility.builder()
                .name(name)
                .type(type)
                .description(description)
                .isAccessible(isAccessible)
                .locationWithinCampus(locationWithinCampus)
                .capacity(capacity)
                .isOperational(isOperational)
                .schoolData(SCHOOL_DATA)
                .build();
    }

    /**
     * Creates a new Power instance with the specified attributes.
     *
     * @param name          the name of the power
     * @param powerLevel    the power level of the power
     * @param description   a description of the power
     * @param category      the category of the power
     * @param isActive      indicates if the power is active
     * @param controlLevel  the control level of the power
     * @param originSource  the origin source of the power
     * @return a fully constructed Power instance
     */
    public static Power createPower(String name, int powerLevel, String description, String category,
                                    boolean isActive, int controlLevel, PowerSource originSource) {
        return Power.builder()
                .name(name)
                .powerLevel(powerLevel)
                .description(description)
                .category(category)
                .isActive(isActive)
                .controlLevel(controlLevel)
                .originSource(originSource)
                .build();
    }

    /**
     * Creates a new Achievement instance with the specified attributes.
     *
     * @param title       the title of the achievement
     * @param description a description of the achievement
     * @param dateAwarded the date when the achievement was awarded
     * @param awardedBy   the person who awarded the achievement
     * @param category    the category of the achievement
     * @return a fully constructed Achievement instance
     */
    public static Achievement createAchievement(String title, String description, LocalDate dateAwarded,
                                                String awardedBy, SubjectCategory category) {
        return Achievement.builder()
                .title(title)
                .description(description)
                .dateAwarded(dateAwarded)
                .awardedBy(awardedBy)
                .category(category)
                .student(STUDENT)
                .build();
    }

    /**
     * Creates a new Lesson instance with the specified start and end time.
     *
     * @param startTime the start time of the lesson
     * @param endTime   the end time of the lesson
     * @return a fully constructed Lesson instance
     */
    public static Lesson createLesson(LocalDateTime startTime, LocalDateTime endTime) {
        return Lesson.builder()
                .startTime(startTime)
                .endTime(endTime)
                .subject(SUBJECT)
                .teacher(TEACHER)
                .students(Collections.singletonList(STUDENT))
                .build();
    }

    /**
     * Creates a new Subject instance with the specified name.
     *
     * @param name the name of the subject
     * @return a fully constructed Subject instance
     */
    public static Subject createSubject(String name) {
        return Subject.builder()
                .name(name)
                .lessons(Collections.singletonList(LESSON))
                .schoolData(SCHOOL_DATA)
                .teachers(Collections.singletonList(TEACHER))
                .build();
    }

    /**
     * Creates a new Teacher instance with the specified attributes.
     *
     * @param firstName        the first name of the teacher
     * @param lastName         the last name of the teacher
     * @param alias            the alias of the teacher
     * @param email            the email address of the teacher
     * @param phoneNumber      the phone number of the teacher
     * @param address          the address of the teacher
     * @param qualifications   the qualifications of the teacher
     * @param yearsOfExperience the years of experience of the teacher
     * @param department       the department of the teacher
     * @param isActive         indicates if the teacher is active
     * @param missionHistory   a list of mission history of the teacher
     * @return a fully constructed Teacher instance
     */
    public static Teacher createTeacher(String firstName, String lastName, String alias, String email,
                                        String phoneNumber, String address, String qualifications,
                                        int yearsOfExperience, String department, boolean isActive,
                                        List<String> missionHistory) {
        return Teacher.builder()
                .firstName(firstName)
                .lastName(lastName)
                .alias(alias)
                .email(email)
                .phoneNumber(phoneNumber)
                .address(address)
                .qualifications(qualifications)
                .yearsOfExperience(yearsOfExperience)
                .department(department)
                .isActive(isActive)
                .missionHistory(missionHistory)
                .schoolData(SCHOOL_DATA)
                .build();
    }

    /**
     * Creates a new SchoolData instance with the specified attributes.
     *
     * @param schoolName      the name of the school
     * @param location        the location of the school
     * @param motto           the motto of the school
     * @param yearEstablished the year when the school was established
     * @param affiliation      the affiliation of the school
     * @param contactInfo     the contact information of the school
     * @param isActive        indicates if the school is active
     * @return a fully constructed SchoolData instance
     */
    public static SchoolData createSchoolData(String schoolName, String location, String motto, int yearEstablished,
                                              String affiliation, String contactInfo, boolean isActive) {
        return SchoolData.builder()
                .schoolName(schoolName)
                .location(location)
                .motto(motto)
                .yearEstablished(yearEstablished)
                .affiliation(affiliation)
                .contactInfo(contactInfo)
                .isActive(isActive)
                .students(Collections.singletonList(STUDENT))
                .teachers(Collections.singletonList(TEACHER))
                .facilities(Collections.singletonList(FACILITY))
                .subjects(Collections.singletonList(SUBJECT))
                .build();
    }

    /**
     * Creates a new Student instance with the specified attributes.
     *
     * @param firstName             the first name of the student
     * @param lastName              the last name of the student
     * @param alias                 the alias or codename of the student
     * @param guardianFirstName     the first name of the student's guardian
     * @param guardianLastName      the last name of the student's guardian
     * @param guardianContactNumber the contact number of the student's guardian
     * @param guardianEmail         the email address of the student's guardian
     * @param contactNumber         the contact number of the student
     * @param email                 the email address of the student
     * @param isActive              true if the student is currently active, false otherwise
     * @param status                the current enrollment status of the student
     * @param missionHistory        a list of missions the student has participated in
     * @return a fully constructed Student instance with associated power, school data, lessons, and achievements
     */

    public static Student createStudent(String firstName, String lastName, String alias,
                                        String guardianFirstName, String guardianLastName,
                                        String guardianContactNumber, String guardianEmail,
                                        String contactNumber, String email, boolean isActive,
                                        Status status, List<String> missionHistory) {
        return Student.builder()
                .firstName(firstName)
                .lastName(lastName)
                .alias(alias)
                .guardianFirstName(guardianFirstName)
                .guardianLastName(guardianLastName)
                .guardianContactNumber(guardianContactNumber)
                .guardianEmail(guardianEmail)
                .contactNumber(contactNumber)
                .email(email)
                .power(POWER)
                .isActive(isActive)
                .schoolData(SCHOOL_DATA)
                .lessons(Collections.singletonList(LESSON))
                .achievements(Collections.singletonList(ACHIEVEMENT))
                .status(status)
                .missionHistory(missionHistory)
                .build();
    }
}

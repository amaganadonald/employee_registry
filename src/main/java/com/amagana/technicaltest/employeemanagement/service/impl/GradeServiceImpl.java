package com.amagana.technicaltest.employeemanagement.service.impl;



import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final PageDtoMapper<GradeProjection> pageDtoMapper;
    private final EntityManager entityManager;

    private static final GradeMapper gradeMapper = GradeMapper.instance;
    @Override
    public List<GradeDtoResponse> getAllGrade() {
        return gradeRepository.findAll()
                .stream()
                .map(gradeMapper::gradeToGradeDtoResponse)
                .toList();
    }

    @Override
    public PageResultDto<GradeProjection> getGradePage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("gradeName").ascending());
        Page<GradeProjection> gradeProjections =  gradeRepository.findAllBy(pageable);
        return pageDtoMapper.createPageResult(gradeProjections);
    }

    @Override
    public GradeDtoResponse getGradeById(Long id) {
        return gradeMapper.gradeToGradeDtoResponse(findGradeById(id));
    }

    @Override
    public Grade findGradeById(Long id) {
        return gradeRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Grade not found with id: " + id));
    }

    @Override
    public GradeDtoResponse createGrade(GradeDtoRequest gradeDtoRequest) {
        return gradeMapper.gradeToGradeDtoResponse(
                gradeRepository.save(gradeMapper.gradeDtoRequestToGrade(gradeDtoRequest))
        );
    }

    @Override
    public GradeDtoResponse updateGrade(Long id, GradeDtoRequest gradeDtoRequest) {
        Grade grade = findGradeById(id);
        grade.setGradeName(gradeDtoRequest.gradeName());
        return gradeMapper.gradeToGradeDtoResponse(
                gradeRepository.save(grade)
        );
    }

    @Override
    public void deleteGrade(Long id) {
       gradeRepository.delete(findGradeById(id));
    }

    @Override
    public List<GradeDtoResponse> createListGrade(List<GradeDtoRequest> gradeDtoRequest) {
        List<Grade> grades = gradeDtoRequest.stream().map(gradeMapper::gradeDtoRequestToGrade).toList();
        return gradeRepository.saveAll(grades).stream()
                .map(gradeMapper::gradeToGradeDtoResponse)
                .toList();
    }
    public List<GradeDtoResponse> advancedSearchGrade(GradeDtoRequest gradeDtoRequest) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Grade> criteriaQuery = criteriaBuilder.createQuery(Grade.class);
        Root<Grade> root = criteriaQuery.from(Grade.class);
        criteriaQuery.select(root);
        List<Predicate> predicates = new ArrayList<>();
        if(gradeDtoRequest.gradeName() != null) {
            predicates.add(criteriaBuilder.like(root.get("gradeName"), "%" + gradeDtoRequest.gradeName()+"%"));
        }
        if(gradeDtoRequest.toDate() != null) {
            predicates.add(criteriaBuilder.equal(root.get("toDate"), gradeDtoRequest.toDate()));
        }
        if(!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }
        return gradeRepository.findAll((Sort) criteriaQuery).stream().map(gradeMapper::gradeToGradeDtoResponse).toList();
    }
}
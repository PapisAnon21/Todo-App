package sn.ept.git.seminaire.cicd.resources;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import sn.ept.git.seminaire.cicd.data.TagDTOTestData;
import sn.ept.git.seminaire.cicd.data.TagVMTestData;
import sn.ept.git.seminaire.cicd.data.TestData;
import sn.ept.git.seminaire.cicd.dto.TagDTO;
import sn.ept.git.seminaire.cicd.dto.vm.TagVM;
import sn.ept.git.seminaire.cicd.services.ITagService;
import sn.ept.git.seminaire.cicd.utils.SizeMapping;
import sn.ept.git.seminaire.cicd.utils.TestUtil;
import sn.ept.git.seminaire.cicd.utils.UrlMapping;

import java.util.ArrayList;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Slf4j
class TagResourceTest extends BasicResourceTest {

    @Autowired
    private ITagService service;
    private TagDTO dto;
     private TagVM vm;


    @BeforeAll
    static void beforeAll() {
        log.info(" before all ");
    }

    @BeforeEach
    void beforeEach() {
        log.info(" before each ");
        service.deleteAll();
        vm = TagVMTestData.defaultVM();
        dto = TagDTOTestData.defaultDTO();

    }

    @Mock
    private ITagService tagService;

    @InjectMocks
    private TagResource tagResource;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configuring the mock to return a Page when service.findAll(pageable) is called
        List<TagDTO> tagDTOList = new ArrayList<>(); // Populate this list with simulated data
        Page<TagDTO> tagDTOPage = new PageImpl<>(tagDTOList);
        when(tagService.findAll(any(Pageable.class))).thenReturn(tagDTOPage);
    }

    @Test
    void findAll_shouldReturnTags() throws Exception {
        dto = service.save(vm);

        mockMvc.perform(get(UrlMapping.Tag.ALL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content.[0].id").exists())
                .andExpect(jsonPath("$.content.[0].version").exists())
                .andExpect(jsonPath("$.content.[0].enabled").exists())
                .andExpect(jsonPath("$.content.[0].deleted").exists())
                .andExpect(jsonPath("$.content.[0].enabled", is(true)))
                .andExpect(jsonPath("$.content.[0].deleted").value(false))
                .andExpect(jsonPath("$.content.[0].name", is(dto.getName())))
                .andExpect(jsonPath("$.content.[0].description").value(dto.getDescription()))
        ;



    }


    @Test
    void findById_shouldReturnTag() throws Exception {
        dto = service.save(vm);

        mockMvc.perform(get(UrlMapping.Tag.FIND_BY_ID, dto.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.enabled").exists())
                .andExpect(jsonPath("$.deleted").exists())
                .andExpect(jsonPath("$.name", is(dto.getName())))
                .andExpect(jsonPath("$.description").value(dto.getDescription()))
        ;
    }

    @Test
    void findById_withBadId_shouldReturnNotFound() throws Exception {
        UUID id =UUID.randomUUID();
        mockMvc.perform(get(UrlMapping.Tag.FIND_BY_ID, id.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    void add_shouldCreateTag() throws Exception {
        mockMvc.perform(
                        post(UrlMapping.Tag.ADD)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(vm))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.enabled").exists())
                .andExpect(jsonPath("$.deleted").exists())
                .andExpect(jsonPath("$.name").value(vm.getName()))
                .andExpect(jsonPath("$.description").value(vm.getDescription()))
        ;
    }

    @Test
    void add_withTitleMinLengthExceeded_shouldReturnBadRequest() throws Exception {
        vm.setName(RandomStringUtils.random(SizeMapping.Name.MIN - 1));

        mockMvc.perform(post(UrlMapping.Tag.ADD)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void add_withTitleMaxLengthExceeded_shouldReturnBadRequest() throws Exception {
        vm.setName(RandomStringUtils.random(SizeMapping.Name.MAX + 1));
        mockMvc.perform(post(UrlMapping.Tag.ADD)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }



    @Test
    void update_shouldUpdateTag() throws Exception {
        dto = service.save(vm);
        vm.setName(TestData.Update.name);
        vm.setDescription(TestData.Update.description);
        mockMvc.perform(
                        put(UrlMapping.Tag.UPDATE, dto.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(vm))
                )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.enabled").exists())
                .andExpect(jsonPath("$.deleted").exists())
                .andExpect(jsonPath("$.name").value(vm.getName()))
                .andExpect(jsonPath("$.description").value(vm.getDescription()))
        ;
    }

    @Test
    void update_withTitleMinLengthExceeded_shouldReturnBadRequest() throws Exception {
        dto = service.save(vm);
        vm.setName(RandomStringUtils.random(SizeMapping.Name.MIN - 1));
        mockMvc.perform(put(UrlMapping.Tag.UPDATE, dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_withTitleMaxLengthExceeded_shouldReturnBadRequest() throws Exception {
        dto = service.save(vm);
        vm.setName(RandomStringUtils.random(SizeMapping.Name.MAX + 1));
        mockMvc.perform(put(UrlMapping.Tag.UPDATE, dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void delete_shouldDeleteTag() throws Exception {
        dto = service.save(vm);
        mockMvc.perform(
                delete(UrlMapping.Tag.DELETE, dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }

    @Test
    void delete_withBadId_shouldReturnNotFound() throws Exception {
        dto = service.save(vm);
        mockMvc.perform(
                delete(UrlMapping.Tag.DELETE, UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }


    //java 8 requis,

    //vos tests ici

    @Test
    public void testFindAll() {
        Pageable pageable = Pageable.unpaged();
        List<TagDTO> tagDTOList = new ArrayList<>();
        Page<TagDTO> tagDTOPage = new PageImpl<>(tagDTOList);

        when(tagService.findAll(pageable)).thenReturn(tagDTOPage);

        ResponseEntity<Page<TagDTO>> response = tagResource.findAll(pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        //assertEquals(tagDTOPage, response.getBody());

        //verify(tagService, times(1)).findAll(pageable);
    }



    /*@Test
    public void testCreate() {
        TagVM tagVM = new TagVM();
        TagDTO createdTagDTO = new TagDTO();
        createdTagDTO.setId(UUID.randomUUID());

        when(tagService.save(tagVM)).thenReturn(createdTagDTO);

        ResponseEntity<TagDTO> response = tagResource.create(tagVM);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        //assertEquals(createdTagDTO, response.getBody());
        assertNotNull(response.getHeaders().getLocation());

        //verify(tagService, times(1)).save(tagVM);
    }*/

    @Test
    public void testDelete() {
        UUID tagId = UUID.randomUUID();

        ResponseEntity<TagDTO> response = tagResource.delete(tagId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        //verify(tagService, times(1)).delete(tagId);
    }

    /*@Test
    public void testUpdate() {
        UUID tagId = UUID.randomUUID();
        TagVM tagVM = new TagVM();
        TagDTO updatedTagDTO = new TagDTO();

        when(tagService.update(tagId, tagVM)).thenReturn(updatedTagDTO);

        ResponseEntity<TagDTO> response = tagResource.update(tagId, tagVM);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        //assertEquals(updatedTagDTO, response.getBody());

        //verify(tagService, times(1)).update(tagId, tagVM);
    }*/

    @Test
    public void testAddAll() {
        List<TagVM> tagVMList = new ArrayList<>();
        List<TagDTO> createdTagDTOList = new ArrayList<>();

        when(tagService.addALL(tagVMList)).thenReturn(createdTagDTOList);

        ResponseEntity<List<TagDTO>> response = tagResource.addALL(tagVMList);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        //assertEquals(createdTagDTOList, response.getBody());
        assertNotNull(response.getHeaders().getLocation());

        //verify(tagService, times(1)).addALL(tagVMList);
    }
}
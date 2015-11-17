package ca.ulaval.glo4003.b6.housematch.dto.assembler;

import ca.ulaval.glo4003.b6.housematch.domain.picture.InactivePicture;
import ca.ulaval.glo4003.b6.housematch.dto.InactivePictureDto;

public class InactivePictureAssembler {

   public InactivePictureAssembler() {

   }

   public InactivePicture assembleInactivePicture(InactivePictureDto inactivePictureDto) {

      InactivePicture inactivePicture = new InactivePicture(inactivePictureDto.getUid(),
            inactivePictureDto.getAddress(), inactivePictureDto.getName());
      return inactivePicture;
   }

   public InactivePictureDto assembleInactivePictureDto(InactivePicture inactivePicture) {

      InactivePictureDto inactivePictureDto = new InactivePictureDto(inactivePicture.getUid(),
            inactivePicture.getAddress(), inactivePicture.getName());
      return inactivePictureDto;
   }

}

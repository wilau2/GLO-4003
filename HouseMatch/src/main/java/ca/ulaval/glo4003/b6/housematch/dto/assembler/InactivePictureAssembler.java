package ca.ulaval.glo4003.b6.housematch.dto.assembler;

import ca.ulaval.glo4003.b6.housematch.domain.picture.Picture;
import ca.ulaval.glo4003.b6.housematch.dto.InactivePictureDto;

public class InactivePictureAssembler {

   public InactivePictureAssembler() {

   }

   public Picture assembleInactivePicture(InactivePictureDto inactivePictureDto) {

      Picture inactivePicture = new Picture(inactivePictureDto.getUid(), inactivePictureDto.getAddress(),
            inactivePictureDto.getName(), inactivePictureDto.getActive());
      return inactivePicture;
   }

   public InactivePictureDto assembleInactivePictureDto(Picture inactivePicture) {

      InactivePictureDto inactivePictureDto = new InactivePictureDto(inactivePicture.getUid(),
            inactivePicture.getAddress(), inactivePicture.getName(), inactivePicture.getActive().toString());
      return inactivePictureDto;
   }

}
